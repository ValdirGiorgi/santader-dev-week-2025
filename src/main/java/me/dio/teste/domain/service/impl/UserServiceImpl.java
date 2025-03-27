package me.dio.teste.domain.service.impl;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import me.dio.teste.domain.model.User;
import me.dio.teste.domain.repository.UserRepository;
import me.dio.teste.domain.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        logger.info("Buscando usuário por ID: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Usuário não encontrado com ID: {}", id);
                    return new NoSuchElementException("Usuário não encontrado com ID: " + id);
                });
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public User create(User user) {
        logger.info("Iniciando criação de usuário");
        
        if (user == null) {
            logger.error("Tentativa de criar usuário com objeto nulo");
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        
        if (user.getAccount() == null) {
            logger.error("Tentativa de criar usuário sem conta");
            throw new IllegalArgumentException("Dados da conta são obrigatórios");
        }
        
        if (user.getAccount().getNumber() == null) {
            logger.error("Tentativa de criar usuário com número de conta nulo");
            throw new IllegalArgumentException("Número da conta é obrigatório");
        }
        
        logger.info("Verificando se já existe conta com número: {}", user.getAccount().getNumber());
        
        if (userRepository.existsByAccount_Number(user.getAccount().getNumber())) {
            logger.error("Número da conta já existe: {}", user.getAccount().getNumber());
            throw new IllegalArgumentException("Número da conta já existe: " + user.getAccount().getNumber());
        }
        
        // Garantir que estamos criando uma nova entidade, não atualizando existente
        user.setId(null);
        
        // Garantir que os objetos relacionados também sejam novas entidades
        if (user.getAccount() != null) {
            user.getAccount().setId(null);
        }
        
        if (user.getCard() != null) {
            user.getCard().setId(null);
        }
        
        // Limpar IDs de features
        if (user.getFeatures() != null) {
            user.getFeatures().forEach(feature -> feature.setId(null));
        }
        
        // Limpar IDs de news
        if (user.getNews() != null) {
            user.getNews().forEach(news -> news.setId(null));
        }
        
        try {
            logger.info("Salvando usuário no banco de dados");
            User savedUser = userRepository.save(user);
            logger.info("Usuário criado com sucesso com ID: {}", savedUser.getId());
            return savedUser;
        } catch (Exception e) {
            logger.error("Erro ao salvar usuário no banco de dados: {}", e.getMessage(), e);
            throw new RuntimeException("Falha ao criar usuário: " + e.getMessage(), e);
        }
    }
}

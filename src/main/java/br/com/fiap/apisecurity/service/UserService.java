package br.com.fiap.apisecurity.service;

import br.com.fiap.apisecurity.entity.User;
import br.com.fiap.apisecurity.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
private final UserRepository userRepository;
@Autowired
    public UserService(UserRepository userRepository){
    this.userRepository=userRepository;
}
@Transactional
@CachePut(value = "users",key = "#result.id")
public User createUser(User user){
    return userRepository.save(user);

}
@Cacheable(value = "users",key = "#id")
public User readUserById(UUID Id){
    return userRepository.findById(Id).orElse(null);
}
@Cacheable(value = "users",key = "'all'")
public List<User> readUsers(){
    return userRepository.findAll();
}
@Transactional
@CachePut(value = "products" ,key ="#result.id")
public User updateUser(UUID Id,User user){
    Optional<User> userOptional=userRepository.findById(Id);
    if(userOptional.isEmpty()){
        return null;
    }
    user.setId(Id);
    return  userRepository.save(user);
}

@CacheEvict(value = "users",key = "#id")
public void deleteUser(UUID Id){
    userRepository.deleteById(Id);
    cleanCacheAllUsers();
    cleanAllCacheUsers();
}

@CacheEvict(value = "users",key = "all")
public void cleanCacheAllUsers(){

}
@CacheEvict(value = "users",allEntries = true)
public void cleanAllCacheUsers(){

    }

}

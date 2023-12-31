package com.example.co2.Service;

import com.example.co2.Dao.UserRepository;
import com.example.co2.Entite.Userco2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MailSenderService mailSending;



    public List<Userco2> getAllUser() {
        return  userRepository.findAll();
    }


    public Userco2 getUserById (Long idProvider){
        return  userRepository.findById(idProvider).orElseThrow(()-> new IllegalArgumentException("Provider ID not Found"));
    }
    public Userco2 deleteUser(Long id){
        Optional<Userco2> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }else
        {
            return null;
        }
    }



    public void validInscription(Long id) {
        Optional<Userco2> user=userRepository.findById(id);
        Userco2 user1=user.get();
        String Newligne = System.getProperty("line.separator");
        String url = "http://localhost:4200/auth/verification/" +user1.getToken();
        String body = "Welcom to our platform \n  use this link to verify your account is :" + Newligne + url;
        if(user.isPresent()) {

            user1.setValid(true);
            this.userRepository.save(user1);
            try {
                mailSending.send(user1.getEmail(), "Welcome Provaider", body);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}

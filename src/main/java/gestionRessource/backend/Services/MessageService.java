package gestionRessource.backend.Services;


import gestionRessource.backend.Models.Message;
import gestionRessource.backend.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public void vue(int id_msg){
        Message message = messageRepository.findMessageByid(id_msg);
        if( message != null){
            message.setVue(true);
            messageRepository.save(message);
        }
    }
}

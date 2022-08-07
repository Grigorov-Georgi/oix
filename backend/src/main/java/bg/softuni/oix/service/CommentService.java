package bg.softuni.oix.service;

import bg.softuni.oix.exception.ObjectNotFoundException;
import bg.softuni.oix.model.entity.CommentEntity;
import bg.softuni.oix.model.entity.OfferEntity;
import bg.softuni.oix.model.entity.UserEntity;
import bg.softuni.oix.repository.CommentRepository;
import bg.softuni.oix.repository.OfferRepository;
import bg.softuni.oix.repository.UserRepository;
import bg.softuni.oix.service.dto.CommentDTO;
import bg.softuni.oix.service.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final OfferRepository offerRepository;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper, UserRepository userRepository, OfferRepository offerRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
    }

    public void save(CommentDTO commentDTO,  long senderId, long offerId){
        CommentEntity commentEntity = commentMapper.commentDTOToCommentEntity(commentDTO);

        UserEntity sender = userRepository.findById(senderId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found!"));
        commentEntity.setSender(sender);

        OfferEntity offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new ObjectNotFoundException("Offer not found!"));
        commentEntity.setOffer(offer);

        commentRepository.save(commentEntity);
    }
}

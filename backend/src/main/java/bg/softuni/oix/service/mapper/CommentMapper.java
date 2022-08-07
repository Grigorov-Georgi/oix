package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.CommentEntity;
import bg.softuni.oix.service.UserService;
import bg.softuni.oix.service.views.CommentView;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        uses = UserService.class
)
public interface CommentMapper {
    CommentView commentEntityToCommentView(CommentEntity commentEntity);

    @AfterMapping
    default void setSenderFullName(@MappingTarget CommentView commentView,
                                  CommentEntity commentEntity){
        commentView.setSenderFullName(commentEntity.getSender().getFirstName() + " " +
                commentEntity.getSender().getLastName());
    }
}

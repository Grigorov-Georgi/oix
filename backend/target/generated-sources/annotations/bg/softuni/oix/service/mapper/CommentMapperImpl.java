package bg.softuni.oix.service.mapper;

import bg.softuni.oix.model.entity.CommentEntity;
import bg.softuni.oix.service.views.CommentView;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-07T21:01:54+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentView commentEntityToCommentView(CommentEntity commentEntity) {
        if ( commentEntity == null ) {
            return null;
        }

        CommentView commentView = new CommentView();

        commentView.setDescription( commentEntity.getDescription() );

        setSenderFullName( commentView, commentEntity );

        return commentView;
    }
}

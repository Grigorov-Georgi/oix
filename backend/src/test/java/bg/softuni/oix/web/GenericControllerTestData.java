package bg.softuni.oix.web;

import bg.softuni.oix.model.entity.*;
import bg.softuni.oix.model.enums.CategoryEnum;
import bg.softuni.oix.model.enums.UserRoleEnum;
import bg.softuni.oix.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GenericControllerTestData {

    private LocationRepository locationRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private OfferRepository offerRepository;
    private CommentRepository commentRepository;

    public GenericControllerTestData(LocationRepository locationRepository, CategoryRepository categoryRepository,
                                     UserRepository userRepository, UserRoleRepository userRoleRepository,
                                     OfferRepository offerRepository, CommentRepository commentRepository) {
        this.locationRepository = locationRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.offerRepository = offerRepository;
        this.commentRepository = commentRepository;
    }

    public void init() {
        //init location
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setCity("Sofia");

        locationEntity = locationRepository.save(locationEntity);
        LocationEntity locationById = locationRepository
                .findById(locationEntity.getId())
                .get();

        //init category
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(CategoryEnum.CAR);
        categoryEntity = categoryRepository.save(categoryEntity);
        CategoryEntity categoryById  = categoryRepository
                .findById(categoryEntity.getId())
                .get();

        //init user roles
        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setUserRole(UserRoleEnum.USER);
        userRole = userRoleRepository.save(userRole);

        UserRoleEntity adminRole = new UserRoleEntity();
        adminRole.setUserRole(UserRoleEnum.ADMIN);
        adminRole = userRoleRepository.save(adminRole);

        //init user
        List<UserRoleEntity> adminRoles = new ArrayList<>();
        adminRoles.add(userRoleRepository.findById(userRole.getId()).get());
        adminRoles.add(userRoleRepository.findById(adminRole.getId()).get());

        List<UserRoleEntity> userRoles = new ArrayList<>();
        userRoles.add(userRoleRepository.findById(userRole.getId()).get());

        UserEntity admin = new UserEntity();
        admin.setEmail("asen.asenov@gmail.com");
        admin.setFirstName("Asen");
        admin.setLastName("Asenov");
        admin.setPassword("topsecret");
        admin.setUserRoles(adminRoles);
        admin = userRepository.save(admin);

        UserEntity user = new UserEntity();
        user.setEmail("user@mail.com");
        user.setFirstName("User");
        user.setLastName("Userov");
        user.setPassword("topsecret");
        user.setUserRoles(userRoles);
        user = userRepository.save(user);

        //init offers
        OfferEntity offer_1 = new OfferEntity();
        offer_1.setLocation(locationById);
        offer_1.setCategory(categoryById);
        offer_1.setSeller(userRepository.findById(user.getId()).get());
        offer_1.setPrice(BigDecimal.valueOf(123));
        offer_1.setDescription("Very good!");
        offer_1.setTitle("Table");
        offer_1.setReleaseDate(LocalDate.of(2022, 12,12));
        offer_1.setUrlPicture("asdasd.com");
        offerRepository.save(offer_1);

        OfferEntity offer_2 = new OfferEntity();
        offer_2.setLocation(locationById);
        offer_2.setCategory(categoryById);
        offer_2.setSeller(userRepository.findById(user.getId()).get());
        offer_2.setPrice(BigDecimal.valueOf(321));
        offer_2.setDescription("Very good quality!");
        offer_2.setTitle("Broken table");
        offer_2.setReleaseDate(LocalDate.of(2022, 12,12));
        offer_2.setUrlPicture("dsadsa.com");
        offerRepository.save(offer_2);

        OfferEntity offer_3 = new OfferEntity();
        offer_3.setLocation(locationById);
        offer_3.setCategory(categoryById);
        offer_3.setSeller(userRepository.findById(user.getId()).get());
        offer_3.setPrice(BigDecimal.valueOf(321));
        offer_3.setDescription("Very good quality!");
        offer_3.setTitle("Broken table");
        offer_3.setReleaseDate(LocalDate.of(2022, 12,12));
        offer_3.setUrlPicture("dsadsa.com");
        offerRepository.save(offer_3);

        //init comment
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setSender(user);
        commentEntity.setDescription("Comment");
        commentEntity.setOffer(offer_1);
        commentRepository.save(commentEntity);
    }

    void cleanUp(){
        commentRepository.deleteAll();
        offerRepository.deleteAll();
        userRepository.deleteAll();
        locationRepository.deleteAll();
        categoryRepository.deleteAll();
        userRoleRepository.deleteAll();
    }
}

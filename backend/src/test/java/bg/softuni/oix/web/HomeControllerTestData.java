package bg.softuni.oix.web;

import bg.softuni.oix.model.entity.*;
import bg.softuni.oix.model.enums.CategoryEnum;
import bg.softuni.oix.model.enums.UserRoleEnum;
import bg.softuni.oix.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HomeControllerTestData {

    private LocationRepository locationRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private OfferRepository offerRepository;

    public HomeControllerTestData(LocationRepository locationRepository, CategoryRepository categoryRepository, UserRepository userRepository, UserRoleRepository userRoleRepository, OfferRepository offerRepository) {
        this.locationRepository = locationRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.offerRepository = offerRepository;
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
        UserRoleEntity user = new UserRoleEntity();
        user.setUserRole(UserRoleEnum.USER);
        user = userRoleRepository.save(user);

        UserRoleEntity admin = new UserRoleEntity();
        admin.setUserRole(UserRoleEnum.ADMIN);
        admin = userRoleRepository.save(admin);

        //init user
        List<UserRoleEntity> roles = new ArrayList<>();
        roles.add(userRoleRepository.findById(user.getId()).get());
        roles.add(userRoleRepository.findById(admin.getId()).get());

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("asen.asenov@gmail.com");
        userEntity.setFirstName("Asen");
        userEntity.setLastName("Asenov");
        userEntity.setPassword("topsecret");
        userEntity.setUserRoles(roles);
        userEntity = userRepository.save(userEntity);

        //init offers
        OfferEntity offer_1 = new OfferEntity();
        offer_1.setLocation(locationById);
        offer_1.setCategory(categoryById);
        offer_1.setSeller(userRepository.findById(userEntity.getId()).get());
        offer_1.setPrice(BigDecimal.valueOf(123));
        offer_1.setDescription("Very good!");
        offer_1.setTitle("Table");
        offer_1.setReleaseDate(LocalDate.of(2022, 12,12));
        offer_1.setUrlPicture("asdasd.com");
        offerRepository.save(offer_1);

        OfferEntity offer_2 = new OfferEntity();
        offer_2.setLocation(locationById);
        offer_2.setCategory(categoryById);
        offer_2.setSeller(userRepository.findById(userEntity.getId()).get());
        offer_2.setPrice(BigDecimal.valueOf(321));
        offer_2.setDescription("Very good quality!");
        offer_2.setTitle("Broken table");
        offer_2.setReleaseDate(LocalDate.of(2022, 12,12));
        offer_2.setUrlPicture("dsadsa.com");
        offerRepository.save(offer_2);

        OfferEntity offer_3 = new OfferEntity();
        offer_3.setLocation(locationById);
        offer_3.setCategory(categoryById);
        offer_3.setSeller(userRepository.findById(userEntity.getId()).get());
        offer_3.setPrice(BigDecimal.valueOf(321));
        offer_3.setDescription("Very good quality!");
        offer_3.setTitle("Broken table");
        offer_3.setReleaseDate(LocalDate.of(2022, 12,12));
        offer_3.setUrlPicture("dsadsa.com");
        offerRepository.save(offer_3);
    }

    void cleanUp(){
        offerRepository.deleteAll();
        userRepository.deleteAll();
        locationRepository.deleteAll();
        categoryRepository.deleteAll();
        userRoleRepository.deleteAll();
    }
}

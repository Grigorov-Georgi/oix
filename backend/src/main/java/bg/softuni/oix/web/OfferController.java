package bg.softuni.oix.web;

import bg.softuni.oix.exception.ObjectNotFoundException;
import bg.softuni.oix.model.entity.OfferEntity;
import bg.softuni.oix.model.user.OixUserDetails;
import bg.softuni.oix.service.CategoryService;
import bg.softuni.oix.service.CommentService;
import bg.softuni.oix.service.LocationService;
import bg.softuni.oix.service.OfferService;
import bg.softuni.oix.service.dto.AddOfferDTO;
import bg.softuni.oix.service.dto.CommentDTO;
import bg.softuni.oix.service.mapper.OfferMapper;
import bg.softuni.oix.service.views.OfferView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.crypto.spec.OAEPParameterSpec;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;
    private final LocationService locationService;
    private final CategoryService categoryService;
    private final CommentService commentService;
    private final OfferMapper offerMapper;

    public OfferController(OfferService offerService, LocationService locationService, CategoryService categoryService, CommentService commentService, OfferMapper offerMapper) {
        this.offerService = offerService;
        this.locationService = locationService;
        this.categoryService = categoryService;
        this.commentService = commentService;
        this.offerMapper = offerMapper;
    }

//    @ModelAttribute("addOfferDTO")
//    public AddOfferDTO initAddOfferDTO() {
//        return new AddOfferDTO();
//    }

    @ModelAttribute("commentDTO")
    public CommentDTO initCommentDTO() {
        return new CommentDTO();
    }

    @GetMapping
    public String allOffers(Model model,
                            @PageableDefault(
                                    sort = "price",
                                    direction = Sort.Direction.ASC,
                                    page = 0,
                                    size = 5
                            ) Pageable pageable) {
        model.addAttribute("offers", this.offerService.getAllOffers(pageable));

        return "offers";
    }

    @GetMapping("/add")
    public String addOfferPage(Model model) {
        model.addAttribute("locations", locationService.getAllLocations());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "add-offer";
    }

    @PostMapping("/add")
    public String addOffer(@Valid AddOfferDTO addOfferDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal OixUserDetails userDetails) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOfferDTO", addOfferDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO",
                    bindingResult);
            return "redirect:/offers/add";
        }
        this.offerService.save(addOfferDTO, userDetails);
        return "redirect:/offers";
    }

    @GetMapping("/{id}/details")
    public String getOfferDetails(@PathVariable("id") long id, Model model) {
        OfferEntity offerEntity = offerService.findOfferEntityById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Offer with id " + id + " not found!"));

        model.addAttribute("offer", offerMapper.offerEntityToOfferView(offerEntity));

        return "offer-details";
    }

    @GetMapping("/{id}/edit")
    public String updateOfferView(@PathVariable long id, Model model) {
        if (!model.containsAttribute("addOfferDTO")) {
            OfferEntity offerEntity = offerService.findById(id)
                    .orElseThrow(() -> new ObjectNotFoundException("Offer with id " + id + " not found!"));
            model.addAttribute("addOfferDTO", offerMapper.offerEntityToAddOfferDto(offerEntity));
        }
//        AddOfferDTO addOfferDTO = offerService.findDtoById(id);
//        model.addAttribute("updateOfferDTO", addOfferDTO);
        model.addAttribute("locations", locationService.getAllLocations());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "edit-offer";
    }

    @PatchMapping("/{id}/edit")
    public String updateOffer(@Valid AddOfferDTO addOfferDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              @PathVariable long id,
                              @AuthenticationPrincipal OixUserDetails userDetails) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addOfferDTO", addOfferDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO", bindingResult);
            return String.format("redirect:/offers/%d/edit", id);
        }

//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("updateOfferDTO", addOfferDTO);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateOfferDTO",
//                    bindingResult);
//            return String.format("redirect:/offers/%d/edit", id);
//        }

        offerService.update(addOfferDTO);

        return "redirect:/offers";
    }

    //TODO: May be a problem - additional annotation may be needed
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/delete")
    public String deleteOffer(@PathVariable long id) {
        OfferEntity offerEntity = offerService.findById(id).orElseThrow(() -> new ObjectNotFoundException("Offer with id " + id + " not found!"));
        offerService.deleteOffer(offerEntity);
        return "redirect:/offers";
    }

    @GetMapping("/{id}/buy")
    public String buyOffer(@PathVariable long id,
                           @AuthenticationPrincipal OixUserDetails userDetails) {
        OfferEntity offerEntity = offerService.findById(id).orElseThrow(() -> new ObjectNotFoundException("Offer with id " + id + " not found!"));
        offerService.buyOffer(offerEntity, userDetails.getId());
        return "redirect:/offers";
    }

    @GetMapping("/my")
    public String getMyOffers(@AuthenticationPrincipal OixUserDetails userDetails, Model model) {
        List<OfferView> myOffers = offerService.getMyOffers(userDetails.getId());
        model.addAttribute("offers", myOffers);
        return "my-offers";
    }

    @GetMapping("/bought-items")
    public String getBoughtItems(@AuthenticationPrincipal OixUserDetails userDetails, Model model) {
        List<OfferView> boughtItems = offerService.getBoughtItems(userDetails.getId());
        model.addAttribute("offers", boughtItems);
        return "bought-items";
    }

    @PostMapping("/{id}/comment")
    public String comment(@Valid CommentDTO commentDTO, @PathVariable Long id, @AuthenticationPrincipal OixUserDetails userDetails) {
        OfferEntity offerEntity = offerService.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Offer with id " + id + " not found!"));
        this.commentService.save(commentDTO, userDetails.getId(), offerEntity.getId());
        return "redirect:/offers/" + id + "/details";
    }

    @GetMapping("/search")
    public String searchOffers(@RequestParam String title,
                               Model model){
        List<OfferView> allByTitle = this.offerService.getAllByTitle(title);
        model.addAttribute("offers", allByTitle);
        return "offer-search";
    }

}

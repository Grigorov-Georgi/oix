package bg.softuni.oix.web;

import bg.softuni.oix.model.user.OixUserDetails;
import bg.softuni.oix.service.CategoryService;
import bg.softuni.oix.service.CommentService;
import bg.softuni.oix.service.LocationService;
import bg.softuni.oix.service.OfferService;
import bg.softuni.oix.service.dto.AddOfferDTO;
import bg.softuni.oix.service.dto.CommentDTO;
import bg.softuni.oix.service.views.OfferView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;
    private final LocationService locationService;
    private final CategoryService categoryService;
    private final CommentService commentService;

    public OfferController(OfferService offerService, LocationService locationService, CategoryService categoryService, CommentService commentService) {
        this.offerService = offerService;
        this.locationService = locationService;
        this.categoryService = categoryService;
        this.commentService = commentService;
    }

    @ModelAttribute("addOfferDTO")
    public AddOfferDTO initAddOfferDTO() {
        return new AddOfferDTO();
    }

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
        OfferView offerView = offerService.findViewById(id);

        model.addAttribute("offer", offerView);

        return "offer-details";
    }

    @GetMapping("/{id}/edit")
    public String updateOfferView(@PathVariable long id, Model model) {
        AddOfferDTO addOfferDTO = offerService.findDtoById(id);
        model.addAttribute("updateOfferDTO", addOfferDTO);
        model.addAttribute("locations", locationService.getAllLocations());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "edit-offer";
    }

    @PostMapping("/{id}/edit")
    public String updateOffer(@Valid AddOfferDTO addOfferDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              @PathVariable long id,
                              @AuthenticationPrincipal OixUserDetails userDetails) {

        //TODO: RedirectAttributes doesn't work!
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("updateOfferDTO", addOfferDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateOfferDTO",
                    bindingResult);
            return String.format("redirect:/offers/%d/edit", id);
        }
        addOfferDTO.setId(id);
        offerService.save(addOfferDTO, userDetails);

        //TODO: Redirect to offer details
        return "redirect:/offers";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/delete")
    public String deleteOffer(@PathVariable long id) {
        offerService.deleteOffer(id);
        return "redirect:/offers";
    }

    @GetMapping("/{id}/buy")
    public String buyOffer(@PathVariable long id,
                           @AuthenticationPrincipal OixUserDetails userDetails) {
        offerService.buyOffer(id, userDetails.getId());
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
        this.commentService.save(commentDTO, userDetails.getId(), id);
        return "redirect:/offers/" + id + "/details";
    }

}

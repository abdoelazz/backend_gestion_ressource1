package gestionRessource.backend.Presentation;


import gestionRessource.backend.controller.*;
import gestionRessource.backend.model.*;
import gestionRessource.backend.service.Impl.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class Respo {

    @Autowired
    UserController userController;
    @Autowired
    PanneController panneController;
    @Autowired
    DepartementController departementController;
    @Autowired
    PropositionController propositionController;
    @Autowired
    private DetailController detailController;
    @Autowired
    private AppelDoffreController appelDoffreController;
    @Autowired
    private FournisseurServiceImpl fournisseurServiceImpl;
    @Autowired
    private FournisseurController fournisseurController;
    @Autowired
    private RessourceController ressourceController;

    public  Respo(DepartementController departementController, PropositionController propositionController) {
        this.departementController = departementController;
        this.propositionController = propositionController;
    }

    @GetMapping("/newPersonnels")
    public String New(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                if (Objects.equals(user.getRole().toString(),"Responsable"))
                {
                    List<User> users = userController.getAllUsers();
                    session.setAttribute("Users", users);

                    return "responsable/NewPersonnels";}
            } else {

            }
        }
        model.addAttribute("error", "Invalid username or password");
        return "redirect:/login";


    }

    @GetMapping("/personnels")
    public String Personnels(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        List<Departement> departements = departementController.getAllDepartements();
        session.setAttribute("departements", departements);
        if (session != null) {
            User user = (User) session.getAttribute("user");

            if (user != null) {

                if (Objects.equals(user.getRole().toString(),"Responsable"))
                {
                    List<User> users = userController.getAllUsers();
                    session.setAttribute("Users", users);
                    return "responsable/Personnels";}
            } else {

            }
        }
        model.addAttribute("error", "Invalid username or password");
        return "redirect:/login";


    }
    @GetMapping("/Personnels/{login}")
    public String Personnel(@PathVariable("login") String login, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");

            if (user != null) {
                if (Objects.equals(user.getRole().toString(),"Responsable"))
                {
                    User use = userController.getUserByLogin(login);
                    session.setAttribute("user1", use);
                    if(use != null)
                    {
                        return "responsable/Personnel";}
                    return "redirect:/Personnels";
                }
            } else {

            }
        }
        model.addAttribute("error", "Invalid username or password");
        return "redirect:/login";


    }
    @DeleteMapping("/deletePerso/{login}")
    public String delete(@PathVariable("login") String login, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");

            if (user != null) {
                if (Objects.equals(user.getRole().toString(),"Responsable"))
                {
                    userController.DeleteUser(login);

                    return "redirect:/Personnels";
                }
                return "redirect:/login";

            } else {
                return "redirect:/login";

            }
        }
        model.addAttribute("error", "Invalid username or password");
        return "redirect:/login";


    }
    @PostMapping("/modifyPersonnel")
    public String modifyPersonnel(HttpServletRequest request,
                                  @RequestParam Long id,
                                  @RequestParam String login,
                                  @RequestParam String firstname,
                                  @RequestParam String lastname,
                                  @RequestParam String departementId, @RequestParam String role) {
        HttpSession session = request.getSession(false);

        User updatedUser = userController.modifyUser(id, login, firstname, lastname, Long.parseLong(departementId), Role.valueOf(role));

        return "redirect:/Personnels";
    }
    @PostMapping("/modifyPasswordPersonnel")
    public String modifyPasswordPersonnel(HttpServletRequest request,
                                          @RequestParam Long user_id, @RequestParam String password)  {
        HttpSession session = request.getSession(false);

        User updatedUser = userController.modifyPasswordUser(user_id, password) ;

        return "redirect:/Personnels";
    }
    @GetMapping("/Pannes")
    public String Pannes(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");

            if (user != null) {

                if (Objects.equals(user.getRole().toString(),"Responsable"))
                {
                    List<Panne> allPannes = panneController.getPannes();

                    session.setAttribute("list-pannes", allPannes);

                    return "responsable/Pannes";}
            } else {
                return "redirect:login";

            }
        }
        model.addAttribute("error", "Invalid username or password");
        return "redirect:/login";


    }
    @GetMapping("/Panne={id}")
    public String Panne(@PathVariable("id") String id, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {

                if (Objects.equals(user.getRole().toString(),"Responsable"))
                {
                    Panne panne = panneController.getPanneById(Long.parseLong(id));
                    session.setAttribute("panne", panne);
                    if(panne != null)
                    {

                        return "responsable/Panne";}
                    return "redirect:/Pannes";
                }
            } else {

            }
        }
        model.addAttribute("error", "Invalid username or password");
        return "redirect:/login";


    }
    @GetMapping("/Propositions")
    public String Proposition(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                if (Objects.equals(user.getRole().toString(),"Responsable"))
                {
                    List<Proposition> propositions = propositionController.getAllPropositions();
                    session.setAttribute("propositions", propositions);

                    return "responsable/Propositions";}
            } else {
                return "redirect:login";

            }
        }
        model.addAttribute("error", "Invalid username or password");
        return "redirect:/login";


    }
    @GetMapping("/propositions/{id}")
    public String Proposition(@PathVariable("id") String id, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");

            if (user != null) {
                if (Objects.equals(user.getRole().toString(),"Responsable"))
                {
                    Optional<Proposition> proposition = propositionController.getPropositionById (Long.parseLong(id));
                    session.setAttribute("proposition", proposition);
                    if(proposition.isPresent())
                    {List<Detail> propositionDetails = (List<Detail>) detailController.getDetailByProposition(proposition.get().getId());
                        session.setAttribute("propositionDetails",propositionDetails);
                        return "responsable/proposition";}
                    return "redirect:/Propositions";
                }
            } else {

            }
        }
        model.addAttribute("error", "Invalid username or password");
        return "redirect:/login";


    }

    @GetMapping("/AppelDoffres")
    public String AppelDoffres(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");

            if (user != null) {

                if (Objects.equals(user.getRole().toString(),"Responsable"))
                {
                    List<AppelDoffre> appelDoffres = appelDoffreController.getAllAppelDoffres();
                    session.setAttribute("appelDoffres", appelDoffres);

                    return "responsable/AppelDOffres";}
            } else {

            }
        }
        model.addAttribute("error", "Invalid username or password");
        return "redirect:/login";


    }
    @GetMapping("/AppelDoffre={id}")
    public String AppelDoffre(@PathVariable("id") String id, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");

            if (user != null) {
                if (Objects.equals(user.getRole().toString(),"Responsable"))
                {
                    AppelDoffre AppelDoffre = appelDoffreController.getAppelDoffreById(Long.parseLong(id));
                    session.setAttribute("appelDoffre", AppelDoffre);
                    if(AppelDoffre != null)
                    {
                        session.setAttribute("ressources",AppelDoffre.getRessources());
                        session.setAttribute("Ressources",ressourceController.getAllRessources());

                        return "responsable/appelDoffre";}
                    return "redirect:/AppelDoffres";
                }
            } else {

            }
        }
        model.addAttribute("error", "Invalid username or password");
        return "redirect:/login";


    }

    @GetMapping("/Fournisseurs")
    public String Fournisseurs(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");

            if (user != null) {

                if (Objects.equals(user.getRole().toString(),"Responsable"))
                {
                    List<User> users = userController.getAllUsers();
                    session.setAttribute("Users", users);
                    List<Fournisseur> Fournisseurs= new ArrayList<Fournisseur>();
                    for(User use :users){
                        if(fournisseurController.getFournisseurById((use.getId()))!=null)
                            Fournisseurs.add(fournisseurController.getFournisseurById((use.getId())));
                    }
                    session.setAttribute("Users", Fournisseurs);
                    return "responsable/Fournisseurs";}
            } else {

            }
        }
        model.addAttribute("error", "Invalid username or password");
        return "redirect:/login";


    }
    @GetMapping("/Ressources")
    public String Ressources(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");

            if (user != null) {

                if (Objects.equals(user.getRole().toString(),"Responsable"))
                {
                    session.setAttribute("ressources",ressourceController.getAllRessources());


                    return "responsable/Ressources";}
            } else {
                return "redirect:login";

            }
        }
        model.addAttribute("error", "Invalid username or password");
        return "redirect:/login";


    }
    }

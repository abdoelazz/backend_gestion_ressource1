package gestionRessource.backend.Presentation;

import gestionRessource.backend.controller.*;
import gestionRessource.backend.model.*;
import gestionRessource.backend.service.Impl.FournisseurServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



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
            // Redirect to a secure page, or set user in session, etc.

            if (user != null) {
                // Print the attribute to the console
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


    } // Name of the }

    @GetMapping("/Personnels")
    public String Personnels(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        List<Departement> departements = departementController.getAllDepartements();
        session.setAttribute("departements", departements);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            // Redirect to a secure page, or set user in session, etc.

            if (user != null) {
                // Print the attribute to the console

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
        return "responsable/Personnels";
    }


        @GetMapping("/deletePerso/{login}")
        public String delete(@PathVariable("login") String login, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            // Redirect to a secure page, or set user in session, etc.

            if (user != null) {
                // Print the attribute to the console
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


    } // Name of the }
    @PostMapping("/modifyPersonnel")
    public String modifyPersonnel(HttpServletRequest request,
                                  @RequestParam Long id,
                                  @RequestParam String login,
                                  @RequestParam String firstname,
                                  @RequestParam String lastname,
                                  @RequestParam String departementId, @RequestParam String role) {
        HttpSession session = request.getSession(false);

        // Modify the user and update the session attribute
        User updatedUser = userController.modifyUser(id, login, firstname, lastname, Long.parseLong(departementId), Role.valueOf(role));


        // Redirect to the Profile page
        return "redirect:/Personnels";
    }
    @PostMapping("/modifyPasswordPersonnel")
    public String modifyPasswordPersonnel(HttpServletRequest request,
                                          @RequestParam Long user_id, @RequestParam String password)  {
        HttpSession session = request.getSession(false);

        // Modify the user and update the session attribute
        User updatedUser = userController.modifyPasswordUser(user_id, password) ;

        // Redirect to the Profile page
        return "redirect:/Personnels";
    }
    @GetMapping("/Pannes")
    public String Pannes(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            // Redirect to a secure page, or set user in session, etc.

            if (user != null) {
                // Print the attribute to the console

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


    } // Name of the }
    @GetMapping("/Panne={id}")
    public String Panne(@PathVariable("id") String id, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            // Redirect to a secure page, or set user in session, etc.

            if (user != null) {
                // Print the attribute to the console
                if (Objects.equals(user.getRole().toString(),"Responsable"))
                {
                    Panne panne = panneController.getPanneById(Long.parseLong(id));
                    session.setAttribute("panne", panne);
                    if(panne != null)
                    {
                        System.out.println("dkhelt");
                        return "responsable/Panne";}
                    return "redirect:/Pannes";
                }
            } else {

            }
        }
        model.addAttribute("error", "Invalid username or password");
        return "redirect:/login";


    } // Name of the }
    @GetMapping("/Propositions")
    public String Proposition(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            // Redirect to a secure page, or set user in session, etc.

            if (user != null) {
                // Print the attribute to the console

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


    } // Name of the }
    @GetMapping("/propositions/{id}")
    public String Proposition(@PathVariable("id") String id, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            // Redirect to a secure page, or set user in session, etc.

            if (user != null) {
                // Print the attribute to the console
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
            // Redirect to a secure page, or set user in session, etc.

            if (user != null) {
                // Print the attribute to the console

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


    } // Name of the }
    // Name of the }
    @GetMapping("/AppelDoffre={id}")
    public String AppelDoffre(@PathVariable("id") String id, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            // Redirect to a secure page, or set user in session, etc.

            if (user != null) {
                // Print the attribute to the console
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


    } // Name of the }

    @GetMapping("/Fournisseurs")
    public String Fournisseurs(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            // Redirect to a secure page, or set user in session, etc.

            if (user != null) {
                // Print the attribute to the console

                if (Objects.equals(user.getRole().toString(),"Responsable"))
                {
                    List<User> users = userController.getAllUsers();
                    session.setAttribute("Users", users);
                    List<Fournisseur> Fournisseurs= new ArrayList<Fournisseur>();
                    for(User use :users){
                        if(use.getRole()==Role.Fournisseur)
                            Fournisseurs.add(fournisseurController.getFournisseurById(use.getId()));
                    }
                    session.setAttribute("Users", Fournisseurs);
                    return "responsable/Fournisseurs";}
            } else {

            }
        }
        model.addAttribute("error", "Invalid username or password");
        return "redirect:/login";


    } // Name of the }
    @GetMapping("/Ressources")
    public String Ressources(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            // Redirect to a secure page, or set user in session, etc.

            if (user != null) {
                // Print the attribute to the console

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


    } // Name of the }
    }

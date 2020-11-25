package br.edu.usj.ads.lpii.agenda;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;





@Controller
public class ContatoController {
    
    @Autowired
    ContatoRepository contatoRepository;

    @GetMapping(value="/")
    public ModelAndView getListaTodos() {
        List<Contato> lista = contatoRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("lista", lista);
        return modelAndView;
    }

    @GetMapping(value="/cadastro")
    public ModelAndView getCadastro() {
        Contato contato = new Contato();

        ModelAndView modelAndView = new ModelAndView("cadastro");
        modelAndView.addObject("contato", contato);
        return modelAndView;
    }

    @GetMapping(value="/detalhes/{id}")
    public ModelAndView getMostrarPorId (@PathVariable Long id) {
        //ler do banco
        Contato contato = contatoRepository.findById(id).get();

        ModelAndView modelAndView = new ModelAndView("detalhes");

        //acrescentar na modelandview
        modelAndView.addObject("contato", contato);
        return modelAndView;
    }

    @GetMapping(value="/deletar/{id}")
    public String getDeletar(@PathVariable Long id){
        contatoRepository.deleteById(id);
        return "redirect:/";
    }
    
    @GetMapping(value="/editar/{id}")
    public ModelAndView getEditar(@PathVariable Long id){
        Contato contato = contatoRepository.findById(id).get();
        
        ModelAndView modelAndView = new ModelAndView("cadastro");
        modelAndView.addObject("contato", contato);

        return modelAndView;
    }

    @GetMapping(value="/pesquisar")
    public String getPesquisar() {
        return "pesquisar";
    }
    
    
    @PostMapping(value="/adicionar")
    public String postAdicionaContato(Contato contato) {
        contatoRepository.save(contato);
        return "redirect:/";
    }
    
    @PostMapping(value="/pesquisar")
    public ModelAndView postPesquisar (@RequestParam String nome) {
        List <Contato> lista = contatoRepository.findByNomeContainingIgnoreCaseOrderByNome(nome);
        
        ModelAndView modelAndView = new ModelAndView("pesquisar");
        modelAndView.addObject("nome", nome);
        modelAndView.addObject("lista", lista);
        return modelAndView;
    }
    
}


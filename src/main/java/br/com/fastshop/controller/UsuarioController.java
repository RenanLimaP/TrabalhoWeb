package br.com.fastshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fastshop.model.Usuario;
import br.com.fastshop.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping("/formulario")
	public ModelAndView formularioUsuario() {
		ModelAndView mv = new ModelAndView("usuario/formularioUsuario");
		mv.addObject("usuario", new Usuario());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvarUsuario(Usuario usuario) {
		usuarioService.adicionarUsuario(usuario);
		ModelAndView mv = new ModelAndView("redirect:/usuario/listar");
		return mv;
	}
	
	@GetMapping("/listar")
	public ModelAndView listarUsuario() {
		List<Usuario> usuarios = usuarioService.retornarTodasOsUsuarios();
		ModelAndView mv = new ModelAndView("usuario/listagemUsuario");
		mv.addObject("Usuarios", usuarios);
		return mv;
	}
	
//	@RequestMapping("{id}")
//	public ModelAndView atualizarUsuario(@PathVariable Long id, RedirectAttributes redirectAttributes) {
//		Usuario usuario = usuarioService.buscarPorId(id);
//		ModelAndView mv = new ModelAndView("formulario");
//		mv.addObject("usuario", usuario);
//		redirectAttributes.addFlashAttribute("mensagem","Produto atualizado com Sucesso!");
//
//		return mv;
//	}
//	
//	@RequestMapping("/excluir/{id}")
//	public ModelAndView excluirUsuario(@PathVariable Long id, RedirectAttributes redirectAttributes) {
//		usuarioService.removerUsuario(id);
//		ModelAndView mv = new ModelAndView("redirect:/usuario/listar");
//		redirectAttributes.addFlashAttribute("mensagem","Produto excluído com Sucesso!");
//		return mv;
//	}
	
	@RequestMapping("/login")
	public ModelAndView loginForm() {
		ModelAndView mv = new ModelAndView("usuario/login");
		return mv;
	}
	
	@PostMapping("/logar")
	public ModelAndView logar() {
		ModelAndView mv = new ModelAndView("redirect:/home");
		return mv;
	}
}

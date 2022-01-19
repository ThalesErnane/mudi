package br.com.alura.mvc.mudi.controller;


import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("/home")
public class HomeController {

	// DOC https://www.thymeleaf.org/
	// client-side - redirect:/  always redirect after post
	// server-side - forward:/ 
	// https://getbootstrap.com/docs/4.5/components/navs/
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
	public String home(Model model, Principal principal) { // Principal pega os dados do usuario logado 
		Sort sort = Sort.by("dataDaEntrega").descending(); // tipo de ordenação DESC
		PageRequest paginacao = PageRequest.of(0, 10, sort); // primeira pag, um item por pg, ordem desc
		
		List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.ENTREGUE, paginacao); // nome do user 
		model.addAttribute("pedidos", pedidos);
		
		return "home";
	}
	
}

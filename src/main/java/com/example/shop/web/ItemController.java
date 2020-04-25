package com.example.shop.web;

import com.example.shop.persistence.Item;
import com.example.shop.persistence.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class ItemController {

    final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "shop/index";
    }

    @GetMapping("/add")
    public String add(Item item) {
        return "shop/add";
    }

    @PostMapping("/create")
    public String create(@Valid Item item, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "shop/add";
        }

        itemRepository.save(item);
        model.addAttribute("item", itemRepository.findAll());
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + id));

        model.addAttribute("item", item);
        return "shop/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid Item item,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            item.setId(id);
            return "shop/edit";
        }

        itemRepository.save(item);
        model.addAttribute("items", itemRepository.findAll());
        return "shop/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + id));
        itemRepository.delete(item);
        model.addAttribute("users", itemRepository.findAll());
        return "shop/index";
    }
}

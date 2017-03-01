package com.gwc.controller;

import com.gwc.dao.ReadingListRepository;
import com.gwc.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by gwcheng on 2017/3/1.
 */
@Controller
@RequestMapping("/readingList")
public class ReadingListController {
    @Autowired
    private ReadingListRepository readingListRepository;

    @RequestMapping(value="/{reader}",method = RequestMethod.GET)
    public String readersBooks(@PathVariable("reader") String reader,Model model){
        List<Book> readingList = readingListRepository.findByReader(reader);
        if(readingList!=null){
            model.addAttribute("books",readingList);
        }
        return "readingList";
    }

    @RequestMapping(value="/{reader}",method = RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader,Book book){
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/readingList/{reader}";
    }

}

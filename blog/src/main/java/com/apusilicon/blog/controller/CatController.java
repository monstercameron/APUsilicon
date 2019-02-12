package com.apusilicon.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.apusilicon.blog.classes.imaginery.Category;
import com.apusilicon.blog.data.CategoryDao;

@RestController
@RequestMapping(value = "category")
public class CatController {

    @Autowired
    private CategoryDao cats;

    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Category> viewCats() {
        return cats.findAll();
    }

    /*
     * Adds new categories to the database
     */
    @CrossOrigin
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addCats(@RequestParam String category) {
        try {
            //checks to see if the category already exists
            for (Category cat : cats.findAll()) {
                if(cat.getCategory().equals(category)){
                    return category + " already exists in database.";
                }
            }
            //if the category doesnt exist then add to database
            Category theCat = new Category();
            theCat.setCategory(category);
            cats.save(theCat);
            return category + " Saved to database.";
        } catch (Exception e) {
            //if the category param is missing return error message
            return "error saving to database, bad parameter";
        }
    }

    /*
     * Removes a category from the database
     */
    @CrossOrigin
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String removeCats(
                                @RequestParam String category){
        //check to see if category exists
        try{
            for (Category cat : cats.findAll()) {
                if(cat.getCategory().equals(category)){
                    cats.delete(cat);
                    break;
                }
            }
            return category + " removed from database.";
        }catch(Exception e){
            //if the category param is missing return error message
            return e.toString();
        }
    }

}
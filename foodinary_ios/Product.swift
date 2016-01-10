//
//  Product.swift
//  foodinary_ios
//
//  Created by Izaak Prats on 1/9/16.
//  Copyright © 2016 IJVP. All rights reserved.
//

import Foundation

class Product {
    let name: String
    let description: String
    let ingredients: NSMutableArray
    let nutrients: NSMutableArray
    
    init(name: String, description: String, ingredients: NSMutableArray, nutrients: NSMutableArray) {
        self.name = name
        self.description = description
        self.ingredients = ingredients
        self.nutrients = nutrients
    }
    
    static func getProduct() -> Product {
        let name = "Sample"
        let description = "This is a sample food that has been scanned via UPC"
        
        let ingredients = NSMutableArray()
        ingredients.addObject("Sugar")
        ingredients.addObject("Spice")
        ingredients.addObject("Everything nice")
        
        let nutrients = NSMutableArray()
        nutrients.addObject("Fats")
        nutrients.addObject("Carbs")
        nutrients.addObject("Everything else bad")

        return Product(name: name, description: description, ingredients: ingredients, nutrients: nutrients)
    }
    
    static func parseProductJson(data: NSDictionary) -> Product {
        let name = data.objectForKey("item_name") as! String
        let description = data.objectForKey("item_description") as! String
        
        let ingredients = NSMutableArray(array: (data.objectForKey("nf_ingredient_statement") as! String).componentsSeparatedByString(", "))
        
        let nutrients = NSMutableArray()
        
        let calories = "Calories : \((data.objectForKey("nf_calories") as! NSNumber).stringValue)"
        let sodium = "Sodium : \((data.objectForKey("nf_sodium") as! NSNumber).stringValue)"
        let carbs = "Carbohydrates : \((data.objectForKey("nf_total_carbohydrate") as! NSNumber).stringValue)"
        let sugar = "Sugars : \((data.objectForKey("nf_sugars") as! NSNumber).stringValue)"
        
        nutrients.addObject(calories)
        nutrients.addObject(sodium)
        nutrients.addObject(carbs)
        nutrients.addObject(sugar)
        
        return Product(name: name, description: description, ingredients: ingredients, nutrients: nutrients)
    }
}
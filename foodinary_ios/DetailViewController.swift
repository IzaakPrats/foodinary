//
//  ViewController.swift
//  foodinary_ios
//
//  Created by Izaak Prats on 1/9/16.
//  Copyright © 2016 IJVP. All rights reserved.
//

import UIKit
import Foundation
import Alamofire

class DetailViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var detailLabel: UILabel!
    @IBOutlet weak var ingredientTableView: UITableView!
    @IBOutlet weak var nutrientTableView: UITableView!
    
    let NUTRITIONIX_ID = "bafd9293"
    let NUTRITIONIX_KEY = "444c2a51502358e5bb79b470c0f4d1e6"
    
    
    var product = Product.getProduct()
    var upc: String!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        if (upc != nil) {
            loadProductForUpc(upc)
        }
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func loadUI() {
        nameLabel.text? = product.name
        detailLabel.text? = product.description
        
        ingredientTableView.scrollEnabled = false
        nutrientTableView.scrollEnabled = false
        
        ingredientTableView.reloadData()
        nutrientTableView.reloadData()
    }
    
    func loadProductForUpc(upc: String) {
        Alamofire.request(.GET, getUrlForUpc(upc)).responseJSON{ response in
            self.product = Product.parseProductJson(response.result.value as! NSDictionary)
            self.loadUI()
        }
    }
    
    func getUrlForUpc(upc: String) -> String {
        return "https://api.nutritionix.com/v1_1/item?upc=\(upc)&appId=\(NUTRITIONIX_ID)&appKey=\(NUTRITIONIX_KEY)";
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if (tableView == ingredientTableView) {
            return product.ingredients.count
        }
        
        if (tableView == nutrientTableView) {
            return product.nutrients.count
        }
        
        return 0
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        if (tableView == ingredientTableView) {
            let cell = tableView.dequeueReusableCellWithIdentifier("ingredientCell", forIndexPath: indexPath)
            cell.textLabel!.text? = product.ingredients.objectAtIndex(indexPath.row) as! String
            return cell
        }
        
        if (tableView == nutrientTableView) {
            let cell = tableView.dequeueReusableCellWithIdentifier("nutrientCell", forIndexPath: indexPath)
            cell.textLabel!.text? = product.nutrients.objectAtIndex(indexPath.row) as! String
            return cell
        }
        
        return UITableViewCell()
    }
}


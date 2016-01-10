//
//  ViewController.swift
//  foodinary_ios
//
//  Created by Izaak Prats on 1/9/16.
//  Copyright © 2016 IJVP. All rights reserved.
//

import UIKit

class ScannerViewController: UIViewController {
    
    var upc: String!
    
    @IBAction func clicked() {
        // let dvc = self.storyboard?.instantiateViewControllerWithIdentifier("DetailViewController")
        // self.presentViewController(dvc!, animated: true, completion: nil)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        // 49000036756 is a 2 liter bottle of Cherry Coke.
        upc = "49000036756"
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if (segue.identifier == "detailSegue") {
            let dvc = segue.destinationViewController as! DetailViewController
            dvc.upc = upc
        }
    }
    
}


//
//  ViewController.swift
//  foodinary_ios
//
//  Created by Izaak Prats on 1/9/16.
//  Copyright © 2016 IJVP. All rights reserved.
//

import UIKit

class ScannerViewController: UIViewController {
    
    @IBAction func clicked() {
        let dvc = self.storyboard?.instantiateViewControllerWithIdentifier("DetailViewController") 
        self.presentViewController(dvc!, animated: true, completion: nil)
    }
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
}


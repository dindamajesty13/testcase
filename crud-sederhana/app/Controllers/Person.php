<?php
 
 namespace App\Controllers;

 use CodeIgniter\RESTful\ResourceController;
 use App\Models\PersonModel;
 use App\Models\ProvinsiModel;
 
 class Person extends ResourceController
 {
     public function index()
     {
         $personModel = new PersonModel();
         $provinsiModel = new ProvinsiModel();
 
         // Perform the join operation
         $data = $personModel
             ->select('ktp.*, propinsi.*')
             ->join('propinsi', 'ktp.id_prop = propinsi.id_prop')
             ->first();
 
         return $this->respond($data);
     }
 }

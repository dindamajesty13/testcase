<?php namespace App\Models;

use CodeIgniter\Model;

class PersonModel extends Model {
    protected $table = "ktp";
    protected $primaryKey = "id";
    protected $allowedFields = ["id", "Id_prop", "nik", "nama"];
}

?>
<?php namespace App\Models;

use CodeIgniter\Model;

class ProvinsiModel extends Model {
    protected $table = "propinsi";
    protected $primaryKey = "id_prop";
    protected $allowedFields = ["id_prop", "nama_prop", "jumlah_penduduk"];
}

?>
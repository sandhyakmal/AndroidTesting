package com.example.helloworld

fun main(){
    println("nilai total: "+ tambah(5,2));

    println("nilai total adalah "+ kurang(5,2))

    for (i in 1..5){
        println("Hello ke-$i")
    }

    val alamat : String? //? berarti boleh kosong
    alamat = "Jalan"
    println("Panjang adalah: ${alamat.length}")


}


fun tambah(angka1:Int, angka2: Int):Int{

    return angka1+angka2;
}

fun kurang(angka1:Int, angka2: Int) = angka1 - angka2

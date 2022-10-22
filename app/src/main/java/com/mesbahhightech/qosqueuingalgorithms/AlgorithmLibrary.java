package com.mesbahhightech.qosqueuingalgorithms;

public final class AlgorithmLibrary {
    public static void SPQ() {
    }
    public static void FQ() {
    }
    public static void WFQ() {
    }
    public static void RR() {
    }
    public static void WRR() {
    }
    public static void DRR() {
    }
    public static void DWRR() {
    }
}

//    public function traitement(Request $request)
//    {
//        $tabOrd = array();
//        $tabFiles = $request->all();
//        $ordType = $tabFiles[4]['ord_type'];//$request->input('ord_type');
//        // dd($tabFiles);
//        if ($ordType == 1){ // SPQ
//            $tabOrd =  $this->spq($tabFiles);
//            // dd($tabOrd);
//        }
//        else if ($ordType == 2){ // RR
//            $tabOrd =  $this->rr($tabFiles);
//        }
//        else if ($ordType == 3){ // WRR
//            $tabOrd =  $this->wrr($tabFiles, $tabFiles[4]['wa'], $tabFiles[4]['wb'], $tabFiles[4]['wc']);
//        }
//        else if ($ordType == 4){ // WRR-T
//            $tabOrd =  $this->wrrT($tabFiles, $tabFiles[4]['wa'], $tabFiles[4]['wb'], $tabFiles[4]['wc']);
//        }
//        else if ($ordType == 5){ // DWRR-T
//            $tabOrd =  $this->dwrrT($tabFiles, $tabFiles[4]['wa'], $tabFiles[4]['wb'], $tabFiles[4]['wc']);
//        }
//        else {
//        }
//        return response()->json($tabOrd);
//    }
//
//    public function spq($tabFiles)
//    {
//        $tabOrd = array();
//        $index = 1;
//        $indexA = 0;
//        $indexB = 0;
//        $indexC = 0;
//        $repeat = true;
//        while ($repeat){
//
//            if ($index != $indexA){
//                $indexA++;
//                if (isset($tabFiles[1][$indexA])){
//                    $tabOrd[$index][0]= 'A';
//                    $tabOrd[$index][1]= ($index*0.5) + 0.5;
//                    // $tabOrd[$index][1]= 'i='.$index.' ia='.$indexA.' ib='.$indexB.' ic='.$indexC ;
//                    $tabFiles[1][$indexA] = null;
//                    $index++;
//                }
//            }
//            else if ($index != $indexB){
//                $indexB++;
//                if (isset($tabFiles[2][$indexB])){
//                    $tabOrd[$index][0]= 'B';
//                    $tabOrd[$index][1]= ($index*0.5) + 0.5;
//                    // $tabOrd[$index][1]= 'i='.$index.' ia='.$indexA.' ib='.$indexB.' ic='.$indexC ;
//                    $tabFiles[2][$indexB] = null;
//                    $index++;
//                }
//            }
//            else if ($index != $indexC){
//                $indexC++;
//                if (isset($tabFiles[3][$indexC])){
//                    $tabOrd[$index][0]= 'C';
//                    $tabOrd[$index][1]= ($index*0.5) + 0.5;
//                    // $tabOrd[$index][1]= 'i='.$index.' ia='.$indexA.' ib='.$indexB.' ic='.$indexC ;
//                    $tabFiles[3][$indexC] = null;
//                    $index++;
//                }
//            }
//            else if ((19 <= $indexA) && (19 <= $indexB) && (19 <= $indexC)) $repeat = false;
//            else if (($index == $indexA) && ($index == $indexB) && ($index == $indexC))
//            {
//                $index++;
//            }
//            else $repeat = false;
//        }
//        return $tabOrd;
//    }
//
//    public function rr($tabFiles)
//    {
//        $tabOrd = array();
//        $index = 1;
//        $indexA = 0;
//        $indexB = 0;
//        $indexC = 0;
//        $repeat = true;
//        while ($repeat){
//            do {
//                $indexA++;
//            } while ((!isset($tabFiles[1][$indexA])) && ($indexA < $index));
//            if (isset($tabFiles[1][$indexA])){
//                $tabOrd[$index][0]= 'A';
//                $tabOrd[$index][1]= ($index*0.5)+0.5;
//                // $tabOrd[$index][1]= 'i='.$index.' ia='.$indexA.' ib='.$indexB.' ic='.$indexC ;
//                $index++;
//            }
//            do {
//                $indexB++;
//            } while ((!isset($tabFiles[2][$indexB])) && ($indexB < $index));
//            if (isset($tabFiles[2][$indexB])){
//                $tabOrd[$index][0]= 'B';
//                $tabOrd[$index][1]= ($index*0.5)+0.5;
//                // $tabOrd[$index][1]= 'i='.$index.' ia='.$indexA.' ib='.$indexB.' ic='.$indexC ;
//                $index++;
//            }
//            do {
//                $indexC++;
//            } while ((!isset($tabFiles[3][$indexC])) && ($indexC < $index));
//            if (isset($tabFiles[3][$indexC])){
//                $tabOrd[$index][0]= 'C';
//                $tabOrd[$index][1]= ($index*0.5)+0.5;
//                // $tabOrd[$index][1]= 'i='.$index.' ia='.$indexA.' ib='.$indexB.' ic='.$indexC ;
//                $index++;
//            }
//            // }
//            if (($index == $indexA) && ($index == $indexB) && ($index == $indexC))
//            {
//                $index++;
//            }
//            if (($indexA >= 19)&&($indexB >= 19)&&($indexC >= 19)) {
//                $repeat = false;
//            }
//        }
//        return $tabOrd;
//    }
//
//    public function wrr($tabFiles, $wa, $wb, $wc)
//    {
//        $tabOrd = array();
//        $index = 1;
//        $indexA = 0;
//        $indexB = 0;
//        $indexC = 0;
//        $repeat = true;
//        while ($repeat){
//
//            for ($i=1; $i<=$wa;$i++){
//                if ($indexA < $index){
//                    do {
//                        $indexA++;
//                    } while ((!isset($tabFiles[1][$indexA])) && ($indexA < $index));
//                    if (isset($tabFiles[1][$indexA])){
//                        $tabOrd[$index][0]= 'A';
//                        $tabOrd[$index][1]= ($index*0.5)+0.5;
//                        // $tabOrd[$index][1]= 'i='.$index.' ia='.$indexA.' ib='.$indexB.' ic='.$indexC ;
//                        $index++;
//                    }
//                }
//            }
//
//            for ($i=1; $i<=$wb;$i++){
//                if ($indexB < $index){
//                    do {
//                        $indexB++;
//                    } while ((!isset($tabFiles[2][$indexB])) && ($indexB < $index));
//                    if (isset($tabFiles[2][$indexB])){
//                        $tabOrd[$index][0]= 'B';
//                        $tabOrd[$index][1]= ($index*0.5)+0.5;
//                        // $tabOrd[$index][1]= 'i='.$index.' ia='.$indexA.' ib='.$indexB.' ic='.$indexC ;
//                        $index++;
//                    }
//                }
//            }
//
//            for ($i=1; $i<=$wc;$i++){
//                if ($indexC < $index){
//                    do {
//                        $indexC++;
//                    } while ((!isset($tabFiles[3][$indexC])) && ($indexC < $index));
//                    if (isset($tabFiles[3][$indexC])){
//                        $tabOrd[$index][0]= 'C';
//                        $tabOrd[$index][1]= ($index*0.5)+0.5;
//                        // $tabOrd[$index][1]= 'i='.$index.' ia='.$indexA.' ib='.$indexB.' ic='.$indexC ;
//                        $index++;
//                    }
//                }
//            }
//            if (($index == $indexA) && ($index == $indexB) && ($index == $indexC))
//            {
//                $index++;
//            }
//            if (($indexA >= 19)&&($indexB >= 19)&&($indexC >= 19)) {
//                $repeat = false;
//            }
//        }
//        return $tabOrd;
//    }
//
//    public function wrrT($tabFiles, $wa, $wb, $wc)
//    {
//        $tabOrd = array();
//        $time = 0.5;
//        $indexA = 0;
//        $indexB = 0;
//        $indexC = 0;
//        $indexTabOrd=0;
//
//        $stopLoping=0;
//
//        $repeat = true;
//        while ($repeat){
//
//            for ($i=1; $i<=$wa;$i++){
//                if ($indexA <= ($time * 2 -1)){
//                    do {
//                        $indexA++;
//                    } while ((!isset($tabFiles[1][$indexA])) && ($indexA <= ($time * 2 -1)));
//                    if (isset($tabFiles[1][$indexA])){
//                        $time= $time+ ($tabFiles[1][$indexA]/1000);
//                        $tabOrd[$indexTabOrd][0]= 'A('.$tabFiles[1][$indexA].')';
//                        $tabOrd[$indexTabOrd][1]= round($time, 2);
//                        // $tabOrd[$indexTabOrd][1]= 't='.$time.' ia='.$indexA.' ib='.$indexB.' ic='.$indexC ;
//                        $indexTabOrd++;
//                    }
//                }
//            }
//
//            for ($i=1; $i<=$wb;$i++){
//                if ($indexB <= ($time * 2 -1)){
//                    do {
//                        $indexB++;
//                    } while ((!isset($tabFiles[2][$indexB])) && ($indexB <= ($time * 2 -1)));
//                    if (isset($tabFiles[2][$indexB])){
//                        $time= $time+ ($tabFiles[2][$indexB]/1000);
//                        $tabOrd[$indexTabOrd][0]= 'B('.$tabFiles[2][$indexB].')';
//                        $tabOrd[$indexTabOrd][1]= round($time, 2);
//                        // $tabOrd[$indexTabOrd][1]= 't='.$time.' ia='.$indexA.' ib='.$indexB.' ic='.$indexC ;
//                        $indexTabOrd++;
//                    }
//                }
//            }
//
//            for ($i=1; $i<=$wc;$i++){
//                if ($indexC <= ($time * 2 -1)){
//                    do {
//                        $indexC++;
//                    } while ((!isset($tabFiles[3][$indexC])) && ($indexC <= ($time * 2 -1)));
//                    if (isset($tabFiles[3][$indexC])){
//                        $time= $time+ ($tabFiles[3][$indexC]/1000);
//                        $tabOrd[$indexTabOrd][0]= 'C('.$tabFiles[3][$indexC].')';
//                        $tabOrd[$indexTabOrd][1]= round($time, 2);
//                        // $tabOrd[$indexTabOrd][1]= 't='.$time.' ia='.$indexA.' ib='.$indexB.' ic='.$indexC ;
//                        $indexTabOrd++;
//                    }
//                }
//            }
//
//            if ((!isset($tabFiles[1][$indexA])) && (!isset($tabFiles[2][$indexB])) && (!isset($tabFiles[3][$indexC])))
//            {
//                $time=$time+0.5;
//            }
//            if (($indexA >= 20)&&($indexB >= 20)&&($indexC >= 20)) {
//                $repeat = false;
//            }
//
//
//            $stopLoping++;
//            if ($stopLoping >= 120) {
//                $repeat = false;
//            }
//
//        }
//        return $tabOrd;
//    }
//
//    public function dwrrT($tabFiles, $wa, $wb, $wc)
//    {
//        $tabOrd = array();
//        $time = 0.5;
//        $indexA = 1;
//        $indexB = 1;
//        $indexC = 1;
//        $indexTabOrd=0;
//        $DCa = 0;
//        $DCb = 0;
//        $DCc = 0;
//        $stopLoping=0;
//
//        $repeat = true;
//        while ($repeat){
//            $DCa = $DCa + $wa;
//            $DCb = $DCb + $wb;
//            $DCc = $DCc + $wc;
//
//            while (($indexA <= 20) && ($tabFiles[1][$indexA] <= $DCa) && ($indexA <= $time * 2)){
//                if (isset($tabFiles[1][$indexA])){
//                    $time= $time+ ($tabFiles[1][$indexA]/1000);
//                    $DCa = $DCa - $tabFiles[1][$indexA];
//                    $tabOrd[$indexTabOrd][0]= 'A('.$tabFiles[1][$indexA].')';
//                    $tabOrd[$indexTabOrd][1]= round($time, 2);
//                    // $tabOrd[$indexTabOrd][1]= 't='.$time.' ia='.$indexA.' ib='.$indexB.' ic='.$indexC ;
//                    $indexTabOrd++;
//                    $indexA ++;
//                }
//                else{
//                    $indexA++;
//                }
//            }
//
//            while (($indexB <= 20) && ($tabFiles[2][$indexB] <= $DCb) && ($indexB <= $time * 2 )){
//                if (isset($tabFiles[2][$indexB])){
//                    $time= $time+ ($tabFiles[2][$indexB]/1000);
//                    $DCb = $DCb - $tabFiles[2][$indexB];
//                    $tabOrd[$indexTabOrd][0]= 'B('.$tabFiles[2][$indexB].')';
//                    $tabOrd[$indexTabOrd][1]= round($time, 2);
//                    // $tabOrd[$indexTabOrd][1]= 't='.$time.' ia='.$indexA.' ib='.$indexB.' ic='.$indexC ;
//                    $indexTabOrd++;
//                    $indexB ++;
//                }
//                else{
//                    $indexB++;
//                }
//            }
//
//            while (($indexC <= 20) && ($tabFiles[3][$indexC] <= $DCc) && ($indexC <= $time * 2)){
//                if (isset($tabFiles[3][$indexC])){
//                    $time= $time+ ($tabFiles[3][$indexC]/1000);
//                    $DCc = $DCc - $tabFiles[3][$indexC];
//                    $tabOrd[$indexTabOrd][0]= 'C('.$tabFiles[3][$indexC].')';
//                    $tabOrd[$indexTabOrd][1]= round($time, 2);
//                    // $tabOrd[$indexTabOrd][1]= 't='.$time.' ia='.$indexA.' ib='.$indexB.' ic='.$indexC ;
//                    $indexTabOrd++;
//                    $indexC ++;
//                }
//                else{
//                    $indexC++;
//                }
//            }
//
//            if($indexA == $indexB && $indexA == $indexC){
//                if ((!isset($tabFiles[1][$indexA-1])) && (!isset($tabFiles[2][$indexB-1])) && (!isset($tabFiles[3][$indexC-1])))
//                {
//                    $time=$time+0.5;
//                    $DCa =0;
//                    $DCb =0;
//                    $DCc =0;
//                }
//            }
//            if (($indexA >= 20)&&($indexB >= 20)&&($indexC >= 20)) {
//                $repeat = false;
//            }
//
//            $stopLoping++;
//            if ($stopLoping >= 500) {
//                $repeat = false;
//            }
//        }
//        return $tabOrd;
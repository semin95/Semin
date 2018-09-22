using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zadaca_2_RPR
{
    public static class GlobalneVarijable
    {
       // public static Int32 idProdavac = new Int32();
       // public static Int32 idKupac = new Int32();
        public static double ukupnaCijena = new double();
        public static List<Lijek> lijekovi = new List<Lijek>();
        public static List<Kupac> kupci = new List<Kupac>(); 
        public static List<Prodavac> prodavaci = new List<Prodavac>();
        public static List<RegulacijaPravila> regulacijaPravila = new List<RegulacijaPravila>();
        public static List<string> preporukaZaPenzioner = new List<string>();
        public static List<EvidencijaLijekova> evidencijaLijekova = new List<EvidencijaLijekova>();
        public static List<EvidencijaLijekova> pomocnaEvidencijaLijekova = new List<EvidencijaLijekova>();
    }
}

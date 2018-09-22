using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using sastojci;

namespace Zadaca_2_RPR
{
    public class Lijek
    {
        public string naziv { get; set; }
        public string formaLijeka { get; set; }
        public string tipLijeka { get; set; }
        public double cijena { get; set; }
        public Int32 doza { get; set; } // npr 2 tablete na dan
        public Int32 dostupnaKolicina { get; set; } // broj doza 
        public int pokrivenostOsiguranja { get; set; }
        public bool kategorija { get; set; }
        public int maksUnosZaDjecu { get; set; }
        public List<Sastojak> sastojci = new List<Sastojak>();

        public Lijek(string naziv, string formaLijeka, string tipLijeka, double cijena, Int32 doza,
               Int32 dostupnaKolicina, int pokrivenostOsiguranja, bool kategorija, List<Sastojak> sastojci,
               int maksUnosZaDjecu)
        {
            this.naziv = naziv;
            this.formaLijeka = formaLijeka;
            this.tipLijeka = tipLijeka;
            this.cijena = cijena;
            this.doza = doza;
            this.dostupnaKolicina = dostupnaKolicina;
            this.pokrivenostOsiguranja = pokrivenostOsiguranja;
            this.kategorija = kategorija;
            this.sastojci = sastojci;
            this.maksUnosZaDjecu = maksUnosZaDjecu;
        }
    }
}

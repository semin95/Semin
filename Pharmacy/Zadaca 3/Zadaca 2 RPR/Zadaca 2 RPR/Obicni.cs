using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zadaca_2_RPR
{
    public class Obicni : Kupac
    {
        
        public Obicni(int ID, string ime, string prezime, DateTime datumRodjenja, List<string> listaAlergija)
            : base(ID, ime, prezime, datumRodjenja, listaAlergija) { }

        public override double cijenaLijeka(bool recept, Lijek lijek)
        {
            double cijena = lijek.cijena;
            if (recept == true && lijek.kategorija == true)
                cijena = (cijena / 100) * (100 - lijek.pokrivenostOsiguranja);
            return cijena;
        }
    }
}

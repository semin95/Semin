using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zadaca_2_RPR
{
    public class Prodavac
    {
        public int ID { get; set; }
        public string ime { get;set; }
        public string prezime { get; set; }
        public DateTime datumRodjenja { get; set; }
        public Prodavac(int ID , string ime , string prezime , DateTime datumRodjenja)
        {
            this.ID = ID;
            this.ime = ime;
            this.prezime = prezime;
            this.datumRodjenja = datumRodjenja;
        }
    }
}

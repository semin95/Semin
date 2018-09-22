using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zadaca_2_RPR
{
    public abstract class Kupac : ICijena
    {
        public int ID { get; set; }
        public string ime { get; set; }
        public string prezime { get; set; }
        public DateTime datumRodjenja { get; set; }
        public List<string> listaAlergija { get; set; }

        public Kupac(int ID, string ime, string prezime, DateTime datumRodjenja , List<string> listaAlergija)
        {
            this.ID = ID;
            this.ime = ime;
            this.prezime = prezime;
            this.datumRodjenja = datumRodjenja;
            this.listaAlergija = listaAlergija;
        }
        public abstract double cijenaLijeka(bool recept, Lijek lijek);
    }
}

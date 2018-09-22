using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DodatniPodaci
{
    public class PodaciOKorisniku
    {
        public int id { get; set; }
        public string grad { get; set; }
        public string adresa {get; set;}
        public string brojTelefona { get; set; }
        public string emailAdresa { get; set; }

        public PodaciOKorisniku(int id , string grad , string adresa , string brojTelefona , string emailAdresa)
        {
            this.id = id;
            this.grad = grad;
            this.adresa = adresa;
            this.brojTelefona = brojTelefona;
            this.emailAdresa = emailAdresa;
        }
    }
}

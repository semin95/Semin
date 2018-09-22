using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace sastojci
{
    public class Sastojak
    {
        public string sastojak { get; set; }
        public Int32 kolicina { get; set; }

        public Sastojak(string sastojak, Int32 kolicina, Int32 maksUnosZaDjecu)
        {
            this.sastojak = sastojak;
            this.kolicina = kolicina;
        }
    }
}

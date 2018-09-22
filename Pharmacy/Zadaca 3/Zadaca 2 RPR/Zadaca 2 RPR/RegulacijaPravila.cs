using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zadaca_2_RPR
{
    public class RegulacijaPravila
    {
        public string prviLijek { get; set; }
        public string drugiLijek { get; set; }

        public RegulacijaPravila(string prviLijek , string drugiLijek)
        {
            this.prviLijek = prviLijek;
            this.drugiLijek = drugiLijek;
        }
    }
}

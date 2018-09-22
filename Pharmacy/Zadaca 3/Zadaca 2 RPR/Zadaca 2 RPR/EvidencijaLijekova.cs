using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zadaca_2_RPR
{
    public class EvidencijaLijekova
    {
        public string naziv { get; set; }
        public Int32 kolicinaKupljenogLijeka { get; set; }
        public DateTime datumIVrijemeKupovine { get; set; }
        public Int32 IDkupca { get; set; }
        public string podaciOReceptu { get; set; }
        public Int32 IDprodavaca { get; set; }
        public EvidencijaLijekova(string naziv , Int32 kolicinaKupljenogLijeka, DateTime datumVrijemeKupovine, Int32 IDkupca,
                string podaciOReceptu, Int32 IDprodavaca)
        {
            this.naziv = naziv;
            this.kolicinaKupljenogLijeka = kolicinaKupljenogLijeka;
            this.datumIVrijemeKupovine = datumIVrijemeKupovine;
            this.IDkupca = IDkupca;
            this.podaciOReceptu = podaciOReceptu;
            this.IDprodavaca = IDprodavaca;
        }

    }
}

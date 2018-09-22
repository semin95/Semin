using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Zadaca_2_RPR
{
    public partial class Racun : Form
    {
        public Racun()
        {
            InitializeComponent();
            double ukupnaCijenaBezPopusta = 0;
            double ukupnaCijenaSaPopustom = 0;
            foreach(EvidencijaLijekova e in GlobalneVarijable.pomocnaEvidencijaLijekova)
            {
                Lijek lijek = GlobalneVarijable.lijekovi.Find(x => x.naziv == e.naziv);
                naziv_l.Items.Add(lijek.naziv);
                kolicina_l.Items.Add("KOM " + e.kolicinaKupljenogLijeka);
                cijena_l.Items.Add(lijek.cijena);
                iznos_l.Items.Add(lijek.cijena * e.kolicinaKupljenogLijeka);
                ukupnaCijenaBezPopusta += lijek.cijena * e.kolicinaKupljenogLijeka;
                Kupac kupac = GlobalneVarijable.kupci.Find(x => x.ID == e.IDkupca);
                bool recept = false;
                if(e.podaciOReceptu != "") recept = true;
                ukupnaCijenaSaPopustom += kupac.cijenaLijeka(recept, lijek);

            }
            ukupnoBez_t.Text = Convert.ToString(ukupnaCijenaBezPopusta);
            ukupnoSa_t.Text = Convert.ToString(ukupnaCijenaSaPopustom);
            GlobalneVarijable.pomocnaEvidencijaLijekova.Clear();
        }

        private void izadi_b_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void Racun_Load(object sender, EventArgs e)
        {

        }

 
    }
}

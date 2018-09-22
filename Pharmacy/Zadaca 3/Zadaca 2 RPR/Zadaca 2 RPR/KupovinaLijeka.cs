using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using sastojci;

namespace Zadaca_2_RPR
{
    public partial class KupovinaLijeka : Form
    {
        public bool provjeraRegulacijePravila()
        {
            foreach(EvidencijaLijekova e in GlobalneVarijable.pomocnaEvidencijaLijekova)
            {
                if (pomocnaZaRegulaciju(naziv_c.Text, e.naziv) == false || pomocnaZaRegulaciju(e.naziv, naziv_c.Text) == false)
                    return false;
            }
            return true;
        }

        public bool pomocnaZaRegulaciju(string s1 , string s2)
        {
            foreach(RegulacijaPravila e in GlobalneVarijable.regulacijaPravila)
            {
                MessageBox.Show(e.prviLijek + "  =  " + s1 + "     ,     " + e.drugiLijek + "  =  " + s2);
                if (e.prviLijek == s1 && e.drugiLijek == s2)
                    return false;
            }
            return true;
        }
        public KupovinaLijeka()
        {
            InitializeComponent();
            foreach(Lijek l in GlobalneVarijable.lijekovi)
            {
                if (!naziv_c.Items.Contains(l.naziv))
                {
                    naziv_c.Items.Add(l.naziv);
                }
            }
        }

        private void dodajLijek_b_Click(object sender, EventArgs e)
        {
            Kupac kupac = GlobalneVarijable.kupci.Find(x => x.ID == id_kupca_n.Value);
            Lijek lijek = GlobalneVarijable.lijekovi.Find(x => x.naziv == naziv_c.Text);
            if (kupac is Dijete && kolicina_n.Value > lijek.maksUnosZaDjecu)
            {
                MessageBox.Show("Djeca ne smiju uzeti vise od maksimalnog preporucenog unosa sastojka", "Upozorenje",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            if (kupac is Penzioner && (lijek.dostupnaKolicina == 0 || !DaLiJeKupljen(lijek) || Provjera(lijek)))
            {
                PreporukaIspis preporukaIspis = new PreporukaIspis();
                preporukaIspis.Show();
            }


            if (lijek.dostupnaKolicina == 0)
            {
                MessageBox.Show("Istrosene su zalihe lijeka!", "Upozorenje", MessageBoxButtons.OK, MessageBoxIcon.Error);
                naziv_c.Text = "";
                return;
            }

            if (!naziv_c.Items.Contains(naziv_c.Text))
            {
                naziv_c.Text = "";
                MessageBox.Show("Ne postoji lijek koji ste upisali!", "Upozorenje");
                return;
            }
            string naziv = naziv_c.Text;
            Int32 idProdavac = Convert.ToInt32(id_prodavac_n.Value);
            Int32 idKupca = Convert.ToInt32(id_kupca_n.Value);
            string podaciOReceptu = podaciOReceptu_t.Text;
            Int32 kolicina = Convert.ToInt32(kolicina_n.Value);

            if (podaciOReceptu == "" && lijek.kategorija == true)
            {
                MessageBox.Show("Nedozvoljena je prodaja lijeka bez recepta", "Upozorenje",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            if (!GlobalneVarijable.kupci.Contains(kupac))
            {
                MessageBox.Show("Ne postoji kupac sa unijetim ID-om!", "Upozorenje", MessageBoxButtons.OK,
                    MessageBoxIcon.Error);
                return;
            }
            if (!GlobalneVarijable.prodavaci.Contains(GlobalneVarijable.prodavaci.Find(x => x.ID == idProdavac)))
            {
                MessageBox.Show("Ne postoji prodavac sa unijetim ID-om!", "Upozorenje", MessageBoxButtons.OK,
                    MessageBoxIcon.Error);
                return;
            }
 
            if(provjeraRegulacijePravila() == false)
            {
                MessageBox.Show("Ovaj lijek se ne moze uzeti sa nekim koji ste vec uzeli!", "Upozorenje ",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }   
            
            foreach(string s in kupac.listaAlergija)
            {
                foreach(Sastojak sastojak in lijek.sastojci)
                {
                    if (sastojak.sastojak == s)
                    {
                        MessageBox.Show("Kupac je alergican na neki od sastojaka!", "Upozorenje", MessageBoxButtons.OK,
                            MessageBoxIcon.Error);
                        return;
                    }
                }
            }

            lijek.dostupnaKolicina = lijek.dostupnaKolicina - 1;
            GlobalneVarijable.pomocnaEvidencijaLijekova.Add(new EvidencijaLijekova(naziv, kolicina, DateTime.Now, idKupca, 
                podaciOReceptu,idProdavac ));
            this.Close();
            MessageBox.Show("Dodali ste lijek!" , "Potvrda");
        }

        public bool DaLiJeKupljen(Lijek lijek)
        {
            if(GlobalneVarijable.evidencijaLijekova.Contains(GlobalneVarijable.evidencijaLijekova.Find(x => x.naziv == lijek.naziv)))
                return true;
            return false;
        }

        public bool Provjera(Lijek lijek)
        {
            DateTime c = new DateTime();
            bool a = false;
            EvidencijaLijekova pom = new EvidencijaLijekova("", 0, c, 0, "", 0);
            foreach(EvidencijaLijekova e in GlobalneVarijable.evidencijaLijekova)
            {
                if (e.datumIVrijemeKupovine > pom.datumIVrijemeKupovine)
                {
                    pom = e;
                    a = true;
                }
            }
            if (!a) return false;           
            System.TimeSpan razlika = DateTime.Now - pom.datumIVrijemeKupovine;
            if (lijek.dostupnaKolicina / lijek.doza < Convert.ToDouble(razlika))
                return true;
            return false;
        }

        private void KupovinaLijeka_FormClosing(object sender, FormClosingEventArgs e)
        {
            e.Cancel = true;
        }

      

        private void otkazi_b(object sender, FormClosingEventArgs e)
        {
            this.Close();
        }
    }
}

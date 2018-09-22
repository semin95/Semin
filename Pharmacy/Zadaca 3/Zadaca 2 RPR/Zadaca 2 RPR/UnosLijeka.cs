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
    public partial class UnosLijeka : Form
    {
        bool validacija()
        {

            if (naziv_t.Text == "")
            {
                statusna_linija.Text = "Nije uneseno ime lijeka!";
                error.SetError(this.naziv_t, "Nije uneseno ime lijeka!");
                naziv_t.Focus();
                return false;
            }
    
                

            if (formaLijeka_t.Text == "")
            {
                statusna_linija.Text = "Nije unesena forma lijeka!";
                error.SetError(this.formaLijeka_t, "Nije unesena forma lijeka!");
                formaLijeka_t.Focus();
                return false;
            }

            if (tipLijeka_t.Text == "")
            {
                statusna_linija.Text = "Nije unesen tip lijeka!";
                error.SetError(this.tipLijeka_t, "Nije unesen tip lijeka!");
                tipLijeka_t.Focus();
                return false;
            }

            try
            {
                double cijena = Convert.ToDouble(cijena_t.Text);
            }
            catch(FormatException)
            {
                statusna_linija.Text = "Cijena nije ispravno unesena!";
                error.SetError(this.cijena_t, "Cijena nije ispravno unesena!");
                cijena_t.Focus();
                return false;
            }
            

            return true;
        }
        public UnosLijeka()
        {
            InitializeComponent();
            deset_r.Checked = true;
            ne_r.Checked = true;
        }

        public UnosLijeka(Lijek lijek)
        {
            InitializeComponent();
            naziv_t.Text = lijek.naziv;
            formaLijeka_t.Text = lijek.formaLijeka;
            tipLijeka_t.Text = lijek.tipLijeka;
            cijena_t.Text = Convert.ToString(lijek.cijena);
            doza_n.Value = lijek.doza;
            dostupnaKolicina_n.Value = lijek.dostupnaKolicina;
            if (lijek.pokrivenostOsiguranja == 10) deset_r.Checked = true;
            else deset_r.Checked = false;
            if (lijek.pokrivenostOsiguranja == 20) dvadeset_r.Checked = true;
            else dvadeset_r.Checked = false;
            if
                (lijek.kategorija) da_r.Checked = true;
            else ne_r.Checked = false;
            foreach(Sastojak s in lijek.sastojci)
            {
                sastojci_l.Items.Add(s.sastojak + "," + s.kolicina);
            }
        }
        private void unesiSastojak_b_Click(object sender, EventArgs e)
        {
            sastojci_l.Items.Add(sastojak_t.Text + "," + Convert.ToString(kolicina_n.Value));
            sastojak_t.Text = "";
        }

        private void unesiLijek_b_Click(object sender, EventArgs e)
        {
            if (!validacija())
                return;

            string naziv = naziv_t.Text;
            string formaLijeka = formaLijeka_t.Text;
            string tipLijeka = tipLijeka_t.Text;
            double cijena = Convert.ToDouble(cijena_t.Text); 
            Int32 doza = Convert.ToInt32(doza_n.Value);
            Int32 dostupnaKolicina = Convert.ToInt32(dostupnaKolicina_n.Value);
            Int32 maxUnosZaDjecu = Convert.ToInt32(maxUnosZaDjecu_n.Value);
            int pokrivenostOsiguranja = 0;

            if (deset_r.Checked) pokrivenostOsiguranja = 10;
            else pokrivenostOsiguranja = 20;
            bool recept = false;

            if (da_r.Checked) recept = true;

            List<Sastojak> sastojci = new List<Sastojak>();
            Sastojak pom = new Sastojak("a", 0, 0);
            foreach (string s in sastojci_l.Items)
            {
                string[] p = s.Split(',');
                pom.sastojak = p[0];
                pom.kolicina = Convert.ToInt32(p[1]);
                sastojci.Add(pom);
            }
            GlobalneVarijable.lijekovi.Add(new Lijek(naziv, formaLijeka, tipLijeka, cijena, doza, dostupnaKolicina, pokrivenostOsiguranja,
                recept, sastojci,maxUnosZaDjecu));
            this.Close();
            
            MessageBox.Show("Uspjesno ste unijeli lijek!" , "Potvrda");
        }

        private void naziv_t_TextChanged(object sender, EventArgs e)
        {
            if (naziv_t.Text != "")
            {
                statusna_linija.Text = "";
                error.SetError(this.naziv_t, string.Empty);
            }
        }

        private void formaLijeka_t_TextChanged(object sender, EventArgs e)
        {
            if (formaLijeka_t.Text != "")
            {
                statusna_linija.Text = "";
                error.SetError(this.formaLijeka_t, string.Empty);
            }
        }

        private void tipLijeka_t_TextChanged(object sender, EventArgs e)
        {
            if (tipLijeka_t.Text != "")
            {
                statusna_linija.Text = "";
                error.SetError(this.tipLijeka_t, string.Empty);
            }
        }

        private void cijena_t_TextChanged(object sender, EventArgs e)
        {
            if (cijena_t.Text != "")
            {
                statusna_linija.Text = "";
                error.SetError(this.cijena_t, string.Empty);
            }
        }

        private void UnosLijeka_Load(object sender, EventArgs e)
        {

        }
    }
}

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
    public partial class apoteka : Form
    {
        Button unosPravila_b;
        Button preporuka_b;
        public apoteka()
        {
            InitializeComponent();

            unosPravila_b = new Button();
            unosPravila_b.Text = "Unos pravila";
            unosPravila_b.Location = new Point(279, 402);
            unosPravila_b.Size = new Size(95, 23);
            unosPravila_b.Click += new System.EventHandler(unosPravila_b_Click);
            pocetna.Controls.Add(unosPravila_b);

            preporuka_b = new Button();
            preporuka_b.Text = "Preporuka";
            preporuka_b.Location = new Point(405, 402);
            preporuka_b.Size = new Size(95, 23);
            preporuka_b.Click += new System.EventHandler(preporuka_b_Click);
            pocetna.Controls.Add(preporuka_b);


        }
        private void lijekDodaj_b_Click(object sender, EventArgs e)
        {
            UnosLijeka unos_lijeka = new UnosLijeka();
            unos_lijeka.Show();
        }

        private void dodajOsobu_b_Click(object sender, EventArgs e)
        {
            UnosOsobe unosOsobe = new UnosOsobe();
            unosOsobe.Show();
        }

        private void dodajLijek_b_Click(object sender, EventArgs e)
        {
            KupovinaLijeka kupovinaLijeka = new KupovinaLijeka();
            kupovinaLijeka.Show();
        }

        private void osvjeziListu_b_Click(object sender, EventArgs e)
        {
            foreach(EvidencijaLijekova l in GlobalneVarijable.pomocnaEvidencijaLijekova)
            {
                if(!spisakLijekova_l.Items.Contains(l.naziv))
                {

                    spisakLijekova_l.Items.Add(l.naziv);
                }
            }
        }

        private void obrisiLijek_b_Click(object sender, EventArgs e)
        {
            GlobalneVarijable.lijekovi.RemoveAt(GlobalneVarijable.lijekovi.FindIndex(x => x.naziv == nazivLijeka_t.Text));
            nazivLijeka_t.Text = "";
            MessageBox.Show("Lijek je izbrisan!", "Potvrda");
            obrisiLijek_b.Visible = false;
        }

        private void pretraziLijek_b_Click(object sender, EventArgs e)
        {
            obrisiLijek_b.Visible = false;
            Lijek lijek = GlobalneVarijable.lijekovi.Find(x => x.naziv == nazivLijeka_t.Text);
            try
            {
                if (!GlobalneVarijable.lijekovi.Contains(lijek))
                {
                    nazivLijeka_t.Text = "";
                    throw (new NePostojiLijek("Ne postoji lijek koji ste unijeli!"));
                }
                MessageBox.Show("Lijek je pronaden!", "Potvrda");
                obrisiLijek_b.Visible = true;
            }
            catch (NePostojiLijek izuzetak)
            {
                MessageBox.Show(izuzetak.Message , "Upozorenje!" , MessageBoxButtons.OK , MessageBoxIcon.Error);
            }            
        }

        private void pretraziOsobu_b_Click(object sender, EventArgs e)
        {
            obrisiLijek_b.Visible = false;
            Kupac osoba = GlobalneVarijable.kupci.Find(x => x.ID == Convert.ToInt32(obrisiOsobu_n.Value));
            Prodavac prodavac = GlobalneVarijable.prodavaci.Find(x => x.ID == Convert.ToInt32(obrisiOsobu_n.Value));
            
            try
            {
                if (!GlobalneVarijable.kupci.Contains(osoba) && !GlobalneVarijable.prodavaci.Contains(prodavac))
                {
                    if (!GlobalneVarijable.prodavaci.Contains(prodavac))
                    {
                        obrisiOsobu_n.Value = 0;
                        throw (new NePostojiOsoba("Ne postoji osoba sa ID-om koji ste unijeli!"));
                    }
                }
                obrisiOsobu_b.Visible = true;
            }
            catch(NePostojiOsoba izuzetak)
            {
                obrisiOsobu_b.Visible = false;
                MessageBox.Show(izuzetak.Message , "Upozorenje" , MessageBoxButtons.OK , MessageBoxIcon.Error);
            }
        }

        private void obrisiOsobu_b_Click(object sender, EventArgs e)
        {
            Kupac osoba = GlobalneVarijable.kupci.Find(x => x.ID == Convert.ToInt32(obrisiOsobu_n.Value));
            if (GlobalneVarijable.kupci.Contains(osoba))
            {
                GlobalneVarijable.kupci.RemoveAt(GlobalneVarijable.kupci.FindIndex(
                x => x.ID == Convert.ToInt32(obrisiOsobu_n.Value)));
            }
            else
            {
                GlobalneVarijable.prodavaci.RemoveAt(GlobalneVarijable.kupci.FindIndex(
                x => x.ID == Convert.ToInt32(obrisiOsobu_n.Value)));
            }
            obrisiOsobu_n.Value = 0;
            MessageBox.Show("Osoba je izbrisana!", "Potvrda");
            obrisiOsobu_b.Visible = false;
        }

        private void promjeniLijek_b_Click(object sender, EventArgs e)
        {
            Lijek l = GlobalneVarijable.lijekovi.Find(x => x.naziv == lijekPromjena_t.Text);
            try
            {
                if (!GlobalneVarijable.lijekovi.Contains(l))
                {
                    nazivLijeka_t.Text = "";
                    throw (new NePostojiLijek("Ne postoji lijek koji ste unijeli!"));

                }
            }
            catch (NePostojiLijek izuzetak)
            {
                MessageBox.Show(izuzetak.Message, "Upozorenje!", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
                UnosLijeka lijek = new UnosLijeka(l);
                lijek.Show();
                GlobalneVarijable.lijekovi.RemoveAt(GlobalneVarijable.lijekovi.FindIndex(x => x.naziv == lijekPromjena_t.Text));
        }

        private void promjeniOsoba_b_Click(object sender, EventArgs e)
        {
            Kupac o = GlobalneVarijable.kupci.Find(x => x.ID == promjenaOsoba_n.Value);
            Prodavac p = GlobalneVarijable.prodavaci.Find(x => x.ID == promjenaOsoba_n.Value);

            try
            {
                if (!GlobalneVarijable.kupci.Contains(o) && !GlobalneVarijable.prodavaci.Contains(p))
                    throw (new NePostojiOsoba("Ne postoji osoba sa ID-om koji ste unijeli!"));
                
                if (GlobalneVarijable.kupci.Contains(o))
                {
                    UnosOsobe osoba = new UnosOsobe(o);
                    osoba.Show();
                    GlobalneVarijable.kupci.RemoveAt(GlobalneVarijable.kupci.FindIndex(x => x.ID ==
                       promjenaOsoba_n.Value));
                }
                else
                {
                    UnosOsobe osoba = new UnosOsobe(p);
                    osoba.Show();
                    GlobalneVarijable.prodavaci.RemoveAt(GlobalneVarijable.prodavaci.FindIndex(x => x.ID ==
                       promjenaOsoba_n.Value));
                }
            }
            catch(NePostojiOsoba izuzetak)
            {
                promjenaOsoba_n.Value = 0;
                MessageBox.Show(izuzetak.Message , "Upozorenje" , MessageBoxButtons.OK , MessageBoxIcon.Error);
            }
        }

        private void kupi_b_Click(object sender, EventArgs e)
        {
            foreach(EvidencijaLijekova evidencija in GlobalneVarijable.pomocnaEvidencijaLijekova)
            {
                GlobalneVarijable.evidencijaLijekova.Add(evidencija);
            }
            spisakLijekova_l.Items.Clear();
            Racun racun = new Racun();
            racun.Show();
        }

        private void unosPravila_b_Click(object sender, EventArgs e)
        {
            Pravila pravila = new Pravila();
            pravila.Show();
        }

        private void preporuka_b_Click(object sender, EventArgs e)
        {
            PreporukaZaPenzionere preporukaZaPenzioner = new PreporukaZaPenzionere();
            preporukaZaPenzioner.Show();
        }

        private void apoteka_Load(object sender, EventArgs e)
        {

        }
    }
}

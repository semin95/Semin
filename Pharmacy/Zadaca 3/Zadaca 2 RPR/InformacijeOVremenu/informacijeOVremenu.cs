using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Net;

namespace InformacijeOVremenu
{
    public partial class informacijeOVremenu_u: UserControl
    {
        string dajVjetar(string s)
        {
            string vjetar = "";
            if (s == "s") vjetar = "Jug";
            else if (s == "n") vjetar = "Sjever";
            else if (s == "e") vjetar = "Istok";
            else if (s == "w") vjetar = "Zapad";
            else if (s == "ne") vjetar = "Sjeveroistok";
            else if (s == "nw") vjetar = "Sjeverozapad";
            else if (s == "se") vjetar = "Jugoistok";
            else if (s == "sw") vjetar = "Jugozapad";
            else
                vjetar = "Desila se greska!";
            return vjetar;

        }

        void dajInformacije(string s)
        {
            int index = s.IndexOf("title=") + 7;
            string vrijeme = "";
            for (int i = index; i < s.Length; i++)
            {
                if (s[i] == '"')
                    break;
                vrijeme += s[i];
            }
            opisVremena_t.Text = vrijeme;

            index = s.IndexOf("met-temp") + 10;
            string temperatura = "";
            for (int i = index; i < index + 10; i++)
            {
                if (s[i] == '&')
                    break;
                temperatura += s[i];
            }
            temperatura_t.Text = temperatura;

            index = s.IndexOf("mm") - 5;
            string padavine = "";
            for (int i = index; i < index + 10; i++)
            {
                if (s[i] == '>')
                    continue;
                if (s[i] == '<')
                    break;
                padavine += s[i];
            }
            padavine_t.Text = padavine;

            index = s.IndexOf("m/s") - 6;
            string brzinaVjetra = "";
            for (int i = index; i < index + 10; i++)
            {
                if (s[i] == '>')
                    continue;
                if (s[i] == '<')
                    break;
                brzinaVjetra += s[i];
            }
            brzinaVjetra_t.Text = brzinaVjetra;

            string pom = "";
            s = s.Substring(index);
            index = s.IndexOf("alt=") + 5;
            for (int i = index; i < index + 5; i++)
            {
                if (s[i] == '"')
                    break;
                pom += s[i];
            }
            string smjerVjetra = dajVjetar(pom);
            smjerVjetra_t.Text = smjerVjetra;
        }
        public informacijeOVremenu_u()
        {
            InitializeComponent();
        }

        private void prikazi_b_Click(object sender, EventArgs e)
        {
            if (!unesiGrad_c.Items.Contains(unesiGrad_c.Text))
            {
                errorProvider1.SetError(this.unesiGrad_c, "Pogresan unos!");
                return;
            }
            else
                errorProvider1.SetError(this.unesiGrad_c, string.Empty);
            string url = "http://weather.ba/detaljna-vremenska-prognoza/";
            string grad = unesiGrad_c.Text;
            
            url += grad;

            WebClient client;
            string reply = "";
            //provjera da li ima konekcije
            try
            {
                client = new WebClient();
                reply = client.DownloadString(url);
            }
            catch(Exception ex)
            {
                MessageBox.Show("Provjerite konekciju!", "Upozorenje", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            int sat = DateTime.Now.Hour;

            //da nebi radio sa cijelim stringom uzimam samo dio koji me interesuje
            //uzimam dio koji izgleda ovako    12:00...(informacije za taj sat)...13:00
            int prvi = reply.IndexOf(Convert.ToString(sat) + ":00");
            int drugi = reply.IndexOf(Convert.ToString((sat + 1) % 24) + ":00");

            string s = "";
            for (int i = prvi; i < drugi; i++)
                s += reply[i];
            try
            {
                dajInformacije(s);
            }
            catch (IndexOutOfRangeException)
            {
                MessageBox.Show("Desila se neka greska prilikom dobijanja informacija. Molimo pokusajte kasnije.", "Upozorenje",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
                reply = "";

        }
    }
}

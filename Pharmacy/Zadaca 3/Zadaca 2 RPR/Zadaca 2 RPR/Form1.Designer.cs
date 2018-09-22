namespace Zadaca_2_RPR
{
    partial class apoteka
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.kupovina_t = new System.Windows.Forms.TabPage();
            this.kupovina_g = new System.Windows.Forms.GroupBox();
            this.kupi_b = new System.Windows.Forms.Button();
            this.spisakLijekova_g = new System.Windows.Forms.GroupBox();
            this.osvjeziListu_b = new System.Windows.Forms.Button();
            this.dodajLijek_b = new System.Windows.Forms.Button();
            this.spisakLijekova_l = new System.Windows.Forms.ListBox();
            this.pocetna = new System.Windows.Forms.TabPage();
            this.lijekDodaj_b = new System.Windows.Forms.Button();
            this.dodajOsobu_b = new System.Windows.Forms.Button();
            this.slika = new System.Windows.Forms.PictureBox();
            this.tab = new System.Windows.Forms.TabControl();
            this.pretraga_t = new System.Windows.Forms.TabPage();
            this.pretraga_g = new System.Windows.Forms.GroupBox();
            this.pretragaOsoba_id = new System.Windows.Forms.GroupBox();
            this.obrisiOsobu_b = new System.Windows.Forms.Button();
            this.pretraziOsobu_b = new System.Windows.Forms.Button();
            this.obrisiOsobu_n = new System.Windows.Forms.NumericUpDown();
            this.id_osobe__l = new System.Windows.Forms.Label();
            this.pretragaLijek_g = new System.Windows.Forms.GroupBox();
            this.obrisiLijek_b = new System.Windows.Forms.Button();
            this.pretraziLijek_b = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.nazivLijeka_t = new System.Windows.Forms.TextBox();
            this.promjena_t = new System.Windows.Forms.TabPage();
            this.promjena_g = new System.Windows.Forms.GroupBox();
            this.osobaPromjena_g = new System.Windows.Forms.GroupBox();
            this.promjenaOsoba_n = new System.Windows.Forms.NumericUpDown();
            this.promjeniOsoba_b = new System.Windows.Forms.Button();
            this.promjenaOsoba_l = new System.Windows.Forms.Label();
            this.promjenaLijeka_g = new System.Windows.Forms.GroupBox();
            this.promjeniLijek_b = new System.Windows.Forms.Button();
            this.lijekPromjena_t = new System.Windows.Forms.TextBox();
            this.lijekPromjena_l = new System.Windows.Forms.Label();
            this.dodatniPodaci_t = new System.Windows.Forms.TabPage();
            this.dodatniPodaci_u1 = new DodatniPodaci.dodatniPodaci_u();
            this.kupovina_t.SuspendLayout();
            this.kupovina_g.SuspendLayout();
            this.spisakLijekova_g.SuspendLayout();
            this.pocetna.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.slika)).BeginInit();
            this.tab.SuspendLayout();
            this.pretraga_t.SuspendLayout();
            this.pretraga_g.SuspendLayout();
            this.pretragaOsoba_id.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.obrisiOsobu_n)).BeginInit();
            this.pretragaLijek_g.SuspendLayout();
            this.promjena_t.SuspendLayout();
            this.promjena_g.SuspendLayout();
            this.osobaPromjena_g.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.promjenaOsoba_n)).BeginInit();
            this.promjenaLijeka_g.SuspendLayout();
            this.dodatniPodaci_t.SuspendLayout();
            this.SuspendLayout();
            // 
            // kupovina_t
            // 
            this.kupovina_t.Controls.Add(this.kupovina_g);
            this.kupovina_t.Location = new System.Drawing.Point(4, 22);
            this.kupovina_t.Name = "kupovina_t";
            this.kupovina_t.Padding = new System.Windows.Forms.Padding(3);
            this.kupovina_t.Size = new System.Drawing.Size(533, 470);
            this.kupovina_t.TabIndex = 3;
            this.kupovina_t.Text = "Kupovina";
            this.kupovina_t.UseVisualStyleBackColor = true;
            // 
            // kupovina_g
            // 
            this.kupovina_g.Controls.Add(this.kupi_b);
            this.kupovina_g.Controls.Add(this.spisakLijekova_g);
            this.kupovina_g.Location = new System.Drawing.Point(101, 20);
            this.kupovina_g.Name = "kupovina_g";
            this.kupovina_g.Size = new System.Drawing.Size(323, 407);
            this.kupovina_g.TabIndex = 0;
            this.kupovina_g.TabStop = false;
            // 
            // kupi_b
            // 
            this.kupi_b.Location = new System.Drawing.Point(111, 342);
            this.kupi_b.Name = "kupi_b";
            this.kupi_b.Size = new System.Drawing.Size(94, 38);
            this.kupi_b.TabIndex = 6;
            this.kupi_b.Text = "Kupi ";
            this.kupi_b.UseVisualStyleBackColor = true;
            this.kupi_b.Click += new System.EventHandler(this.kupi_b_Click);
            // 
            // spisakLijekova_g
            // 
            this.spisakLijekova_g.Controls.Add(this.osvjeziListu_b);
            this.spisakLijekova_g.Controls.Add(this.dodajLijek_b);
            this.spisakLijekova_g.Controls.Add(this.spisakLijekova_l);
            this.spisakLijekova_g.Location = new System.Drawing.Point(44, 34);
            this.spisakLijekova_g.Name = "spisakLijekova_g";
            this.spisakLijekova_g.Size = new System.Drawing.Size(234, 245);
            this.spisakLijekova_g.TabIndex = 5;
            this.spisakLijekova_g.TabStop = false;
            this.spisakLijekova_g.Text = "Spisak dodanih lijekova:";
            // 
            // osvjeziListu_b
            // 
            this.osvjeziListu_b.Location = new System.Drawing.Point(128, 195);
            this.osvjeziListu_b.Name = "osvjeziListu_b";
            this.osvjeziListu_b.Size = new System.Drawing.Size(75, 23);
            this.osvjeziListu_b.TabIndex = 4;
            this.osvjeziListu_b.Text = "Osvjezi listu";
            this.osvjeziListu_b.UseVisualStyleBackColor = true;
            this.osvjeziListu_b.Click += new System.EventHandler(this.osvjeziListu_b_Click);
            // 
            // dodajLijek_b
            // 
            this.dodajLijek_b.Location = new System.Drawing.Point(29, 195);
            this.dodajLijek_b.Name = "dodajLijek_b";
            this.dodajLijek_b.Size = new System.Drawing.Size(75, 23);
            this.dodajLijek_b.TabIndex = 1;
            this.dodajLijek_b.Text = "Dodaj lijek";
            this.dodajLijek_b.UseVisualStyleBackColor = true;
            this.dodajLijek_b.Click += new System.EventHandler(this.dodajLijek_b_Click);
            // 
            // spisakLijekova_l
            // 
            this.spisakLijekova_l.FormattingEnabled = true;
            this.spisakLijekova_l.Location = new System.Drawing.Point(29, 29);
            this.spisakLijekova_l.Name = "spisakLijekova_l";
            this.spisakLijekova_l.Size = new System.Drawing.Size(174, 134);
            this.spisakLijekova_l.TabIndex = 0;
            // 
            // pocetna
            // 
            this.pocetna.Controls.Add(this.lijekDodaj_b);
            this.pocetna.Controls.Add(this.dodajOsobu_b);
            this.pocetna.Controls.Add(this.slika);
            this.pocetna.Location = new System.Drawing.Point(4, 22);
            this.pocetna.Name = "pocetna";
            this.pocetna.Padding = new System.Windows.Forms.Padding(3);
            this.pocetna.Size = new System.Drawing.Size(533, 470);
            this.pocetna.TabIndex = 0;
            this.pocetna.Text = "Pocetna";
            this.pocetna.UseVisualStyleBackColor = true;
            // 
            // lijekDodaj_b
            // 
            this.lijekDodaj_b.Location = new System.Drawing.Point(34, 402);
            this.lijekDodaj_b.Name = "lijekDodaj_b";
            this.lijekDodaj_b.Size = new System.Drawing.Size(95, 23);
            this.lijekDodaj_b.TabIndex = 2;
            this.lijekDodaj_b.Text = "Dodaj lijek";
            this.lijekDodaj_b.UseVisualStyleBackColor = true;
            this.lijekDodaj_b.Click += new System.EventHandler(this.lijekDodaj_b_Click);
            // 
            // dodajOsobu_b
            // 
            this.dodajOsobu_b.Location = new System.Drawing.Point(156, 402);
            this.dodajOsobu_b.Name = "dodajOsobu_b";
            this.dodajOsobu_b.Size = new System.Drawing.Size(95, 23);
            this.dodajOsobu_b.TabIndex = 1;
            this.dodajOsobu_b.Text = "Dodaj osobu";
            this.dodajOsobu_b.UseVisualStyleBackColor = true;
            this.dodajOsobu_b.Click += new System.EventHandler(this.dodajOsobu_b_Click);
            // 
            // slika
            // 
            this.slika.BackColor = System.Drawing.Color.Transparent;
            this.slika.Image = global::Zadaca_2_RPR.Properties.Resources.LOGO_pet_plus;
            this.slika.Location = new System.Drawing.Point(10, 52);
            this.slika.Name = "slika";
            this.slika.Size = new System.Drawing.Size(510, 306);
            this.slika.TabIndex = 0;
            this.slika.TabStop = false;
            // 
            // tab
            // 
            this.tab.Controls.Add(this.pocetna);
            this.tab.Controls.Add(this.kupovina_t);
            this.tab.Controls.Add(this.pretraga_t);
            this.tab.Controls.Add(this.promjena_t);
            this.tab.Controls.Add(this.dodatniPodaci_t);
            this.tab.Location = new System.Drawing.Point(2, 0);
            this.tab.Name = "tab";
            this.tab.SelectedIndex = 0;
            this.tab.Size = new System.Drawing.Size(541, 496);
            this.tab.TabIndex = 0;
            // 
            // pretraga_t
            // 
            this.pretraga_t.Controls.Add(this.pretraga_g);
            this.pretraga_t.Location = new System.Drawing.Point(4, 22);
            this.pretraga_t.Name = "pretraga_t";
            this.pretraga_t.Padding = new System.Windows.Forms.Padding(3);
            this.pretraga_t.Size = new System.Drawing.Size(533, 470);
            this.pretraga_t.TabIndex = 4;
            this.pretraga_t.Text = "Pretraga";
            this.pretraga_t.UseVisualStyleBackColor = true;
            // 
            // pretraga_g
            // 
            this.pretraga_g.Controls.Add(this.pretragaOsoba_id);
            this.pretraga_g.Controls.Add(this.pretragaLijek_g);
            this.pretraga_g.Location = new System.Drawing.Point(77, 25);
            this.pretraga_g.Name = "pretraga_g";
            this.pretraga_g.Size = new System.Drawing.Size(368, 408);
            this.pretraga_g.TabIndex = 0;
            this.pretraga_g.TabStop = false;
            this.pretraga_g.Text = "Pretraga";
            // 
            // pretragaOsoba_id
            // 
            this.pretragaOsoba_id.Controls.Add(this.obrisiOsobu_b);
            this.pretragaOsoba_id.Controls.Add(this.pretraziOsobu_b);
            this.pretragaOsoba_id.Controls.Add(this.obrisiOsobu_n);
            this.pretragaOsoba_id.Controls.Add(this.id_osobe__l);
            this.pretragaOsoba_id.Location = new System.Drawing.Point(54, 225);
            this.pretragaOsoba_id.Name = "pretragaOsoba_id";
            this.pretragaOsoba_id.Size = new System.Drawing.Size(255, 163);
            this.pretragaOsoba_id.TabIndex = 1;
            this.pretragaOsoba_id.TabStop = false;
            this.pretragaOsoba_id.Text = "Osoba";
            // 
            // obrisiOsobu_b
            // 
            this.obrisiOsobu_b.Location = new System.Drawing.Point(87, 116);
            this.obrisiOsobu_b.Name = "obrisiOsobu_b";
            this.obrisiOsobu_b.Size = new System.Drawing.Size(75, 23);
            this.obrisiOsobu_b.TabIndex = 3;
            this.obrisiOsobu_b.Text = "Obrisi osobu";
            this.obrisiOsobu_b.UseVisualStyleBackColor = true;
            this.obrisiOsobu_b.Visible = false;
            this.obrisiOsobu_b.Click += new System.EventHandler(this.obrisiOsobu_b_Click);
            // 
            // pretraziOsobu_b
            // 
            this.pretraziOsobu_b.Location = new System.Drawing.Point(87, 67);
            this.pretraziOsobu_b.Name = "pretraziOsobu_b";
            this.pretraziOsobu_b.Size = new System.Drawing.Size(75, 23);
            this.pretraziOsobu_b.TabIndex = 2;
            this.pretraziOsobu_b.Text = "Pretrazi";
            this.pretraziOsobu_b.UseVisualStyleBackColor = true;
            this.pretraziOsobu_b.Click += new System.EventHandler(this.pretraziOsobu_b_Click);
            // 
            // obrisiOsobu_n
            // 
            this.obrisiOsobu_n.Location = new System.Drawing.Point(123, 20);
            this.obrisiOsobu_n.Maximum = new decimal(new int[] {
            100000,
            0,
            0,
            0});
            this.obrisiOsobu_n.Name = "obrisiOsobu_n";
            this.obrisiOsobu_n.Size = new System.Drawing.Size(103, 20);
            this.obrisiOsobu_n.TabIndex = 1;
            // 
            // id_osobe__l
            // 
            this.id_osobe__l.AutoSize = true;
            this.id_osobe__l.Location = new System.Drawing.Point(35, 22);
            this.id_osobe__l.Name = "id_osobe__l";
            this.id_osobe__l.Size = new System.Drawing.Size(53, 13);
            this.id_osobe__l.TabIndex = 0;
            this.id_osobe__l.Text = "ID osobe:";
            // 
            // pretragaLijek_g
            // 
            this.pretragaLijek_g.Controls.Add(this.obrisiLijek_b);
            this.pretragaLijek_g.Controls.Add(this.pretraziLijek_b);
            this.pretragaLijek_g.Controls.Add(this.label1);
            this.pretragaLijek_g.Controls.Add(this.nazivLijeka_t);
            this.pretragaLijek_g.Location = new System.Drawing.Point(54, 34);
            this.pretragaLijek_g.Name = "pretragaLijek_g";
            this.pretragaLijek_g.Size = new System.Drawing.Size(255, 170);
            this.pretragaLijek_g.TabIndex = 0;
            this.pretragaLijek_g.TabStop = false;
            this.pretragaLijek_g.Text = "Lijek";
            // 
            // obrisiLijek_b
            // 
            this.obrisiLijek_b.Location = new System.Drawing.Point(87, 120);
            this.obrisiLijek_b.Name = "obrisiLijek_b";
            this.obrisiLijek_b.Size = new System.Drawing.Size(75, 23);
            this.obrisiLijek_b.TabIndex = 3;
            this.obrisiLijek_b.Text = "Obrisi lijek";
            this.obrisiLijek_b.UseVisualStyleBackColor = true;
            this.obrisiLijek_b.Visible = false;
            this.obrisiLijek_b.Click += new System.EventHandler(this.obrisiLijek_b_Click);
            // 
            // pretraziLijek_b
            // 
            this.pretraziLijek_b.Location = new System.Drawing.Point(87, 72);
            this.pretraziLijek_b.Name = "pretraziLijek_b";
            this.pretraziLijek_b.Size = new System.Drawing.Size(75, 23);
            this.pretraziLijek_b.TabIndex = 2;
            this.pretraziLijek_b.Text = "Pretrazi";
            this.pretraziLijek_b.UseVisualStyleBackColor = true;
            this.pretraziLijek_b.Click += new System.EventHandler(this.pretraziLijek_b_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(24, 33);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(64, 13);
            this.label1.TabIndex = 1;
            this.label1.Text = "Naziv lijeka:";
            // 
            // nazivLijeka_t
            // 
            this.nazivLijeka_t.Location = new System.Drawing.Point(131, 30);
            this.nazivLijeka_t.Name = "nazivLijeka_t";
            this.nazivLijeka_t.Size = new System.Drawing.Size(100, 20);
            this.nazivLijeka_t.TabIndex = 0;
            // 
            // promjena_t
            // 
            this.promjena_t.Controls.Add(this.promjena_g);
            this.promjena_t.Location = new System.Drawing.Point(4, 22);
            this.promjena_t.Name = "promjena_t";
            this.promjena_t.Padding = new System.Windows.Forms.Padding(3);
            this.promjena_t.Size = new System.Drawing.Size(533, 470);
            this.promjena_t.TabIndex = 5;
            this.promjena_t.Text = "Promjena";
            this.promjena_t.UseVisualStyleBackColor = true;
            // 
            // promjena_g
            // 
            this.promjena_g.Controls.Add(this.osobaPromjena_g);
            this.promjena_g.Controls.Add(this.promjenaLijeka_g);
            this.promjena_g.Location = new System.Drawing.Point(77, 25);
            this.promjena_g.Name = "promjena_g";
            this.promjena_g.Size = new System.Drawing.Size(368, 408);
            this.promjena_g.TabIndex = 0;
            this.promjena_g.TabStop = false;
            this.promjena_g.Text = "Promjena";
            // 
            // osobaPromjena_g
            // 
            this.osobaPromjena_g.Controls.Add(this.promjenaOsoba_n);
            this.osobaPromjena_g.Controls.Add(this.promjeniOsoba_b);
            this.osobaPromjena_g.Controls.Add(this.promjenaOsoba_l);
            this.osobaPromjena_g.Location = new System.Drawing.Point(54, 225);
            this.osobaPromjena_g.Name = "osobaPromjena_g";
            this.osobaPromjena_g.Size = new System.Drawing.Size(255, 163);
            this.osobaPromjena_g.TabIndex = 1;
            this.osobaPromjena_g.TabStop = false;
            this.osobaPromjena_g.Text = "Osoba";
            // 
            // promjenaOsoba_n
            // 
            this.promjenaOsoba_n.Location = new System.Drawing.Point(126, 48);
            this.promjenaOsoba_n.Name = "promjenaOsoba_n";
            this.promjenaOsoba_n.Size = new System.Drawing.Size(100, 20);
            this.promjenaOsoba_n.TabIndex = 4;
            // 
            // promjeniOsoba_b
            // 
            this.promjeniOsoba_b.Location = new System.Drawing.Point(87, 105);
            this.promjeniOsoba_b.Name = "promjeniOsoba_b";
            this.promjeniOsoba_b.Size = new System.Drawing.Size(75, 23);
            this.promjeniOsoba_b.TabIndex = 3;
            this.promjeniOsoba_b.Text = "Promjeni";
            this.promjeniOsoba_b.UseVisualStyleBackColor = true;
            this.promjeniOsoba_b.Click += new System.EventHandler(this.promjeniOsoba_b_Click);
            // 
            // promjenaOsoba_l
            // 
            this.promjenaOsoba_l.AutoSize = true;
            this.promjenaOsoba_l.Location = new System.Drawing.Point(26, 50);
            this.promjenaOsoba_l.Name = "promjenaOsoba_l";
            this.promjenaOsoba_l.Size = new System.Drawing.Size(53, 13);
            this.promjenaOsoba_l.TabIndex = 1;
            this.promjenaOsoba_l.Text = "ID osobe:";
            // 
            // promjenaLijeka_g
            // 
            this.promjenaLijeka_g.Controls.Add(this.promjeniLijek_b);
            this.promjenaLijeka_g.Controls.Add(this.lijekPromjena_t);
            this.promjenaLijeka_g.Controls.Add(this.lijekPromjena_l);
            this.promjenaLijeka_g.Location = new System.Drawing.Point(54, 34);
            this.promjenaLijeka_g.Name = "promjenaLijeka_g";
            this.promjenaLijeka_g.Size = new System.Drawing.Size(255, 170);
            this.promjenaLijeka_g.TabIndex = 0;
            this.promjenaLijeka_g.TabStop = false;
            this.promjenaLijeka_g.Text = "Lijek";
            // 
            // promjeniLijek_b
            // 
            this.promjeniLijek_b.Location = new System.Drawing.Point(87, 102);
            this.promjeniLijek_b.Name = "promjeniLijek_b";
            this.promjeniLijek_b.Size = new System.Drawing.Size(75, 23);
            this.promjeniLijek_b.TabIndex = 2;
            this.promjeniLijek_b.Text = "Promjeni";
            this.promjeniLijek_b.UseVisualStyleBackColor = true;
            this.promjeniLijek_b.Click += new System.EventHandler(this.promjeniLijek_b_Click);
            // 
            // lijekPromjena_t
            // 
            this.lijekPromjena_t.Location = new System.Drawing.Point(126, 41);
            this.lijekPromjena_t.Name = "lijekPromjena_t";
            this.lijekPromjena_t.Size = new System.Drawing.Size(100, 20);
            this.lijekPromjena_t.TabIndex = 1;
            // 
            // lijekPromjena_l
            // 
            this.lijekPromjena_l.AutoSize = true;
            this.lijekPromjena_l.Location = new System.Drawing.Point(26, 44);
            this.lijekPromjena_l.Name = "lijekPromjena_l";
            this.lijekPromjena_l.Size = new System.Drawing.Size(64, 13);
            this.lijekPromjena_l.TabIndex = 0;
            this.lijekPromjena_l.Text = "Naziv lijeka:";
            // 
            // dodatniPodaci_t
            // 
            this.dodatniPodaci_t.Controls.Add(this.dodatniPodaci_u1);
            this.dodatniPodaci_t.Location = new System.Drawing.Point(4, 22);
            this.dodatniPodaci_t.Name = "dodatniPodaci_t";
            this.dodatniPodaci_t.Padding = new System.Windows.Forms.Padding(3);
            this.dodatniPodaci_t.Size = new System.Drawing.Size(533, 470);
            this.dodatniPodaci_t.TabIndex = 6;
            this.dodatniPodaci_t.Text = "Dodatni podaci";
            this.dodatniPodaci_t.UseVisualStyleBackColor = true;
            // 
            // dodatniPodaci_u1
            // 
            this.dodatniPodaci_u1.Location = new System.Drawing.Point(4, 4);
            this.dodatniPodaci_u1.Name = "dodatniPodaci_u1";
            this.dodatniPodaci_u1.Size = new System.Drawing.Size(527, 465);
            this.dodatniPodaci_u1.TabIndex = 0;
            // 
            // apoteka
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(544, 480);
            this.Controls.Add(this.tab);
            this.Name = "apoteka";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Apoteka";
            this.Load += new System.EventHandler(this.apoteka_Load);
            this.kupovina_t.ResumeLayout(false);
            this.kupovina_g.ResumeLayout(false);
            this.spisakLijekova_g.ResumeLayout(false);
            this.pocetna.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.slika)).EndInit();
            this.tab.ResumeLayout(false);
            this.pretraga_t.ResumeLayout(false);
            this.pretraga_g.ResumeLayout(false);
            this.pretragaOsoba_id.ResumeLayout(false);
            this.pretragaOsoba_id.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.obrisiOsobu_n)).EndInit();
            this.pretragaLijek_g.ResumeLayout(false);
            this.pretragaLijek_g.PerformLayout();
            this.promjena_t.ResumeLayout(false);
            this.promjena_g.ResumeLayout(false);
            this.osobaPromjena_g.ResumeLayout(false);
            this.osobaPromjena_g.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.promjenaOsoba_n)).EndInit();
            this.promjenaLijeka_g.ResumeLayout(false);
            this.promjenaLijeka_g.PerformLayout();
            this.dodatniPodaci_t.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabPage kupovina_t;
        private System.Windows.Forms.GroupBox kupovina_g;
        private System.Windows.Forms.Button kupi_b;
        private System.Windows.Forms.GroupBox spisakLijekova_g;
        private System.Windows.Forms.Button dodajLijek_b;
        private System.Windows.Forms.ListBox spisakLijekova_l;
        private System.Windows.Forms.TabPage pocetna;
        private System.Windows.Forms.Button lijekDodaj_b;
        private System.Windows.Forms.Button dodajOsobu_b;
        private System.Windows.Forms.PictureBox slika;
        private System.Windows.Forms.TabControl tab;
        private System.Windows.Forms.Button osvjeziListu_b;
        private System.Windows.Forms.TabPage pretraga_t;
        private System.Windows.Forms.GroupBox pretraga_g;
        private System.Windows.Forms.GroupBox pretragaOsoba_id;
        private System.Windows.Forms.GroupBox pretragaLijek_g;
        private System.Windows.Forms.Button obrisiLijek_b;
        private System.Windows.Forms.Button pretraziLijek_b;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox nazivLijeka_t;
        private System.Windows.Forms.NumericUpDown obrisiOsobu_n;
        private System.Windows.Forms.Label id_osobe__l;
        private System.Windows.Forms.Button obrisiOsobu_b;
        private System.Windows.Forms.Button pretraziOsobu_b;
        private System.Windows.Forms.TabPage promjena_t;
        private System.Windows.Forms.GroupBox promjena_g;
        private System.Windows.Forms.GroupBox osobaPromjena_g;
        private System.Windows.Forms.GroupBox promjenaLijeka_g;
        private System.Windows.Forms.NumericUpDown promjenaOsoba_n;
        private System.Windows.Forms.Button promjeniOsoba_b;
        private System.Windows.Forms.Label promjenaOsoba_l;
        private System.Windows.Forms.Button promjeniLijek_b;
        private System.Windows.Forms.TextBox lijekPromjena_t;
        private System.Windows.Forms.Label lijekPromjena_l;
        private System.Windows.Forms.TabPage dodatniPodaci_t;
        private DodatniPodaci.dodatniPodaci_u dodatniPodaci_u1;

    }
}


namespace Zadaca_2_RPR
{
    partial class KupovinaLijeka
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
            this.kupovinaLijeka_g = new System.Windows.Forms.GroupBox();
            this.kolicina_n = new System.Windows.Forms.NumericUpDown();
            this.naziv_l = new System.Windows.Forms.Label();
            this.naziv_c = new System.Windows.Forms.ComboBox();
            this.dodajLijek_b = new System.Windows.Forms.Button();
            this.kolicina_l = new System.Windows.Forms.Label();
            this.podaciOReceptu_t = new System.Windows.Forms.TextBox();
            this.podaciOReceptu_l = new System.Windows.Forms.Label();
            this.id_kupca_n = new System.Windows.Forms.NumericUpDown();
            this.id_kupca_l = new System.Windows.Forms.Label();
            this.id_prodavac_n = new System.Windows.Forms.NumericUpDown();
            this.id_prodavac_l = new System.Windows.Forms.Label();
            this.kupovinaLijeka_g.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.kolicina_n)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.id_kupca_n)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.id_prodavac_n)).BeginInit();
            this.SuspendLayout();
            // 
            // kupovinaLijeka_g
            // 
            this.kupovinaLijeka_g.Controls.Add(this.kolicina_n);
            this.kupovinaLijeka_g.Controls.Add(this.naziv_l);
            this.kupovinaLijeka_g.Controls.Add(this.naziv_c);
            this.kupovinaLijeka_g.Controls.Add(this.dodajLijek_b);
            this.kupovinaLijeka_g.Controls.Add(this.kolicina_l);
            this.kupovinaLijeka_g.Controls.Add(this.podaciOReceptu_t);
            this.kupovinaLijeka_g.Controls.Add(this.podaciOReceptu_l);
            this.kupovinaLijeka_g.Controls.Add(this.id_kupca_n);
            this.kupovinaLijeka_g.Controls.Add(this.id_kupca_l);
            this.kupovinaLijeka_g.Controls.Add(this.id_prodavac_n);
            this.kupovinaLijeka_g.Controls.Add(this.id_prodavac_l);
            this.kupovinaLijeka_g.Location = new System.Drawing.Point(12, 12);
            this.kupovinaLijeka_g.Name = "kupovinaLijeka_g";
            this.kupovinaLijeka_g.Size = new System.Drawing.Size(303, 299);
            this.kupovinaLijeka_g.TabIndex = 0;
            this.kupovinaLijeka_g.TabStop = false;
            // 
            // kolicina_n
            // 
            this.kolicina_n.Location = new System.Drawing.Point(127, 201);
            this.kolicina_n.Maximum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.kolicina_n.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.kolicina_n.Name = "kolicina_n";
            this.kolicina_n.Size = new System.Drawing.Size(120, 20);
            this.kolicina_n.TabIndex = 11;
            this.kolicina_n.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // naziv_l
            // 
            this.naziv_l.AutoSize = true;
            this.naziv_l.Location = new System.Drawing.Point(21, 23);
            this.naziv_l.Name = "naziv_l";
            this.naziv_l.Size = new System.Drawing.Size(37, 13);
            this.naziv_l.TabIndex = 10;
            this.naziv_l.Text = "Naziv:";
            // 
            // naziv_c
            // 
            this.naziv_c.FormattingEnabled = true;
            this.naziv_c.Location = new System.Drawing.Point(127, 20);
            this.naziv_c.Name = "naziv_c";
            this.naziv_c.Size = new System.Drawing.Size(121, 21);
            this.naziv_c.TabIndex = 9;
            // 
            // dodajLijek_b
            // 
            this.dodajLijek_b.Location = new System.Drawing.Point(110, 248);
            this.dodajLijek_b.Name = "dodajLijek_b";
            this.dodajLijek_b.Size = new System.Drawing.Size(75, 23);
            this.dodajLijek_b.TabIndex = 8;
            this.dodajLijek_b.Text = "Dodaj lijek";
            this.dodajLijek_b.UseVisualStyleBackColor = true;
            this.dodajLijek_b.Click += new System.EventHandler(this.dodajLijek_b_Click);
            // 
            // kolicina_l
            // 
            this.kolicina_l.AutoSize = true;
            this.kolicina_l.Location = new System.Drawing.Point(21, 201);
            this.kolicina_l.Name = "kolicina_l";
            this.kolicina_l.Size = new System.Drawing.Size(47, 13);
            this.kolicina_l.TabIndex = 6;
            this.kolicina_l.Text = "Kolicina:";
            // 
            // podaciOReceptu_t
            // 
            this.podaciOReceptu_t.Location = new System.Drawing.Point(127, 152);
            this.podaciOReceptu_t.Name = "podaciOReceptu_t";
            this.podaciOReceptu_t.Size = new System.Drawing.Size(120, 20);
            this.podaciOReceptu_t.TabIndex = 5;
            // 
            // podaciOReceptu_l
            // 
            this.podaciOReceptu_l.AutoSize = true;
            this.podaciOReceptu_l.Location = new System.Drawing.Point(21, 155);
            this.podaciOReceptu_l.Name = "podaciOReceptu_l";
            this.podaciOReceptu_l.Size = new System.Drawing.Size(91, 13);
            this.podaciOReceptu_l.TabIndex = 4;
            this.podaciOReceptu_l.Text = "Podaci o receptu:";
            // 
            // id_kupca_n
            // 
            this.id_kupca_n.Location = new System.Drawing.Point(127, 103);
            this.id_kupca_n.Maximum = new decimal(new int[] {
            100000000,
            0,
            0,
            0});
            this.id_kupca_n.Name = "id_kupca_n";
            this.id_kupca_n.Size = new System.Drawing.Size(120, 20);
            this.id_kupca_n.TabIndex = 3;
            // 
            // id_kupca_l
            // 
            this.id_kupca_l.AutoSize = true;
            this.id_kupca_l.Location = new System.Drawing.Point(21, 105);
            this.id_kupca_l.Name = "id_kupca_l";
            this.id_kupca_l.Size = new System.Drawing.Size(54, 13);
            this.id_kupca_l.TabIndex = 2;
            this.id_kupca_l.Text = "ID kupca:";
            // 
            // id_prodavac_n
            // 
            this.id_prodavac_n.Location = new System.Drawing.Point(127, 60);
            this.id_prodavac_n.Maximum = new decimal(new int[] {
            10000000,
            0,
            0,
            0});
            this.id_prodavac_n.Name = "id_prodavac_n";
            this.id_prodavac_n.Size = new System.Drawing.Size(120, 20);
            this.id_prodavac_n.TabIndex = 1;
            // 
            // id_prodavac_l
            // 
            this.id_prodavac_l.AutoSize = true;
            this.id_prodavac_l.Location = new System.Drawing.Point(21, 62);
            this.id_prodavac_l.Name = "id_prodavac_l";
            this.id_prodavac_l.Size = new System.Drawing.Size(75, 13);
            this.id_prodavac_l.TabIndex = 0;
            this.id_prodavac_l.Text = "ID prodavaca:";
            // 
            // KupovinaLijeka
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(330, 323);
            this.Controls.Add(this.kupovinaLijeka_g);
            this.Name = "KupovinaLijeka";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Kupovina lijeka";
            this.kupovinaLijeka_g.ResumeLayout(false);
            this.kupovinaLijeka_g.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.kolicina_n)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.id_kupca_n)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.id_prodavac_n)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox kupovinaLijeka_g;
        private System.Windows.Forms.NumericUpDown id_kupca_n;
        private System.Windows.Forms.Label id_kupca_l;
        private System.Windows.Forms.NumericUpDown id_prodavac_n;
        private System.Windows.Forms.Label id_prodavac_l;
        private System.Windows.Forms.TextBox podaciOReceptu_t;
        private System.Windows.Forms.Label podaciOReceptu_l;
        private System.Windows.Forms.Label kolicina_l;
        private System.Windows.Forms.Button dodajLijek_b;
        private System.Windows.Forms.Label naziv_l;
        private System.Windows.Forms.ComboBox naziv_c;
        private System.Windows.Forms.NumericUpDown kolicina_n;
    }
}
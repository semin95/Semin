namespace Zadaca_2_RPR
{
    partial class Racun
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
            this.naziv_l = new System.Windows.Forms.ListBox();
            this.kolicina_l = new System.Windows.Forms.ListBox();
            this.cijena_l = new System.Windows.Forms.ListBox();
            this.iznos_l = new System.Windows.Forms.ListBox();
            this.ukupnoBez_t = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.izadi_b = new System.Windows.Forms.Button();
            this.ukupnoSa_t = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // naziv_l
            // 
            this.naziv_l.FormattingEnabled = true;
            this.naziv_l.Location = new System.Drawing.Point(29, 37);
            this.naziv_l.Name = "naziv_l";
            this.naziv_l.Size = new System.Drawing.Size(116, 134);
            this.naziv_l.TabIndex = 0;
            // 
            // kolicina_l
            // 
            this.kolicina_l.FormattingEnabled = true;
            this.kolicina_l.Location = new System.Drawing.Point(151, 37);
            this.kolicina_l.Name = "kolicina_l";
            this.kolicina_l.Size = new System.Drawing.Size(73, 134);
            this.kolicina_l.TabIndex = 1;
            // 
            // cijena_l
            // 
            this.cijena_l.FormattingEnabled = true;
            this.cijena_l.Location = new System.Drawing.Point(230, 37);
            this.cijena_l.Name = "cijena_l";
            this.cijena_l.Size = new System.Drawing.Size(73, 134);
            this.cijena_l.TabIndex = 2;
            // 
            // iznos_l
            // 
            this.iznos_l.FormattingEnabled = true;
            this.iznos_l.Location = new System.Drawing.Point(309, 37);
            this.iznos_l.Name = "iznos_l";
            this.iznos_l.Size = new System.Drawing.Size(73, 134);
            this.iznos_l.TabIndex = 3;
            // 
            // ukupnoBez_t
            // 
            this.ukupnoBez_t.Location = new System.Drawing.Point(282, 177);
            this.ukupnoBez_t.Name = "ukupnoBez_t";
            this.ukupnoBez_t.ReadOnly = true;
            this.ukupnoBez_t.Size = new System.Drawing.Size(100, 20);
            this.ukupnoBez_t.TabIndex = 4;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(26, 21);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(37, 13);
            this.label1.TabIndex = 5;
            this.label1.Text = "Naziv:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(151, 21);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(47, 13);
            this.label2.TabIndex = 6;
            this.label2.Text = "Kolicina:";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(227, 21);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(39, 13);
            this.label3.TabIndex = 7;
            this.label3.Text = "Cijena:";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(306, 21);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(35, 13);
            this.label4.TabIndex = 8;
            this.label4.Text = "Iznos:";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(142, 180);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(134, 13);
            this.label5.TabIndex = 9;
            this.label5.Text = "Ukupno KM (bez popusta):";
            // 
            // izadi_b
            // 
            this.izadi_b.Location = new System.Drawing.Point(47, 195);
            this.izadi_b.Name = "izadi_b";
            this.izadi_b.Size = new System.Drawing.Size(75, 23);
            this.izadi_b.TabIndex = 10;
            this.izadi_b.Text = "Izadi";
            this.izadi_b.UseVisualStyleBackColor = true;
            this.izadi_b.Click += new System.EventHandler(this.izadi_b_Click);
            // 
            // ukupnoSa_t
            // 
            this.ukupnoSa_t.Location = new System.Drawing.Point(282, 210);
            this.ukupnoSa_t.Name = "ukupnoSa_t";
            this.ukupnoSa_t.ReadOnly = true;
            this.ukupnoSa_t.Size = new System.Drawing.Size(100, 20);
            this.ukupnoSa_t.TabIndex = 11;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(142, 213);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(136, 13);
            this.label6.TabIndex = 12;
            this.label6.Text = "Ukupno KM (sa popustom):";
            // 
            // Racun
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(417, 242);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.ukupnoSa_t);
            this.Controls.Add(this.izadi_b);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.ukupnoBez_t);
            this.Controls.Add(this.iznos_l);
            this.Controls.Add(this.cijena_l);
            this.Controls.Add(this.kolicina_l);
            this.Controls.Add(this.naziv_l);
            this.Name = "Racun";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Racun";
            this.Load += new System.EventHandler(this.Racun_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListBox naziv_l;
        private System.Windows.Forms.ListBox kolicina_l;
        private System.Windows.Forms.ListBox cijena_l;
        private System.Windows.Forms.ListBox iznos_l;
        private System.Windows.Forms.TextBox ukupnoBez_t;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Button izadi_b;
        private System.Windows.Forms.TextBox ukupnoSa_t;
        private System.Windows.Forms.Label label6;
    }
}
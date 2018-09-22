namespace Zadaca_2_RPR
{
    partial class Pravila
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
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.unesi_b = new System.Windows.Forms.Button();
            this.nazivDrugogLijeka_t = new System.Windows.Forms.TextBox();
            this.nazivPrvogLijeka_t = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.groupBox1.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.unesi_b);
            this.groupBox1.Controls.Add(this.nazivDrugogLijeka_t);
            this.groupBox1.Controls.Add(this.nazivPrvogLijeka_t);
            this.groupBox1.Controls.Add(this.label2);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.groupBox1.Location = new System.Drawing.Point(0, 0);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(346, 203);
            this.groupBox1.TabIndex = 0;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Unesite nazive lijekova koji se ne mogu zajedno uzeti:";
            // 
            // unesi_b
            // 
            this.unesi_b.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Left | System.Windows.Forms.AnchorStyles.Right)));
            this.unesi_b.Location = new System.Drawing.Point(140, 147);
            this.unesi_b.Name = "unesi_b";
            this.unesi_b.Size = new System.Drawing.Size(80, 23);
            this.unesi_b.TabIndex = 4;
            this.unesi_b.Text = "Unesi";
            this.unesi_b.UseVisualStyleBackColor = true;
            this.unesi_b.Click += new System.EventHandler(this.unesi_b_Click);
            // 
            // nazivDrugogLijeka_t
            // 
            this.nazivDrugogLijeka_t.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Left | System.Windows.Forms.AnchorStyles.Right)));
            this.nazivDrugogLijeka_t.Location = new System.Drawing.Point(159, 113);
            this.nazivDrugogLijeka_t.Name = "nazivDrugogLijeka_t";
            this.nazivDrugogLijeka_t.Size = new System.Drawing.Size(120, 20);
            this.nazivDrugogLijeka_t.TabIndex = 3;
            // 
            // nazivPrvogLijeka_t
            // 
            this.nazivPrvogLijeka_t.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Left | System.Windows.Forms.AnchorStyles.Right)));
            this.nazivPrvogLijeka_t.Location = new System.Drawing.Point(159, 65);
            this.nazivPrvogLijeka_t.Name = "nazivPrvogLijeka_t";
            this.nazivPrvogLijeka_t.Size = new System.Drawing.Size(120, 20);
            this.nazivPrvogLijeka_t.TabIndex = 2;
            // 
            // label2
            // 
            this.label2.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Left | System.Windows.Forms.AnchorStyles.Right)));
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(33, 113);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(100, 13);
            this.label2.TabIndex = 1;
            this.label2.Text = "Naziv drugog lijeka:";
            // 
            // label1
            // 
            this.label1.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Left | System.Windows.Forms.AnchorStyles.Right)));
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(33, 68);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(94, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "Naziv prvog lijeka:";
            // 
            // Pravila
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(346, 203);
            this.Controls.Add(this.groupBox1);
            this.Name = "Pravila";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Unos pravila";
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Button unesi_b;
        private System.Windows.Forms.TextBox nazivDrugogLijeka_t;
        private System.Windows.Forms.TextBox nazivPrvogLijeka_t;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;

    }
}
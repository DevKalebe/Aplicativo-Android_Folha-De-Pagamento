package br.senai.sp.cotia.leafpay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

import br.senai.sp.cotia.leafpay.R;

public class ResultadoActivity extends AppCompatActivity {

    // váriaveis dos componentes da activity
    private TextView tvSalarioBruto, tvPlanoSaude, tvTransporte, tvAlimentacao, tvRefeicao, tvInss, tvIrrf, tvSalarioLiquido, tvPorcentagem;
    private Button btnRecalculate;

    // váriaveis dos tipos de resultado
    private double valorSalarioBruto, valorTransporte, valorRefeicao, valorInss, valorIrrf, valorSalarioLiquido, valorPorcentagem;
    private int valorPlanoSaude, valorAlimentacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        tvSalarioBruto = (TextView) findViewById(R.id.tv_salario_bruto);
        tvPlanoSaude = (TextView) findViewById(R.id.tv_plano_saude);
        tvTransporte = (TextView) findViewById(R.id.tv_transporte);
        tvAlimentacao = (TextView) findViewById(R.id.tv_alimentacao);
        tvRefeicao = (TextView) findViewById(R.id.tv_refeicao);
        tvInss = (TextView) findViewById(R.id.tv_inss);
        tvIrrf = (TextView) findViewById(R.id.tv_irrf);
        tvSalarioLiquido = (TextView) findViewById(R.id.tv_salario_liquido);
        tvPorcentagem = (TextView) findViewById(R.id.tv_porcentagem_desconto);
        btnRecalculate = (Button) findViewById(R.id.btn_recalcular);

        valorSalarioBruto = getIntent().getDoubleExtra("salarioBruto", 0);
        valorPlanoSaude = getIntent().getIntExtra("planoSaude", 0);
        valorTransporte = getIntent().getDoubleExtra("valeTransporte", 0);
        valorAlimentacao = getIntent().getIntExtra("valeAliementacao", 0);
        valorRefeicao = getIntent().getDoubleExtra("valeRefeicao", 0);
        valorInss = getIntent().getDoubleExtra("inss", 0);
        valorIrrf = getIntent().getDoubleExtra("irrf", 0);
        valorSalarioLiquido = getIntent().getDoubleExtra("salarioLiquido", 0);
        valorPorcentagem = getIntent().getDoubleExtra("porcentagemDesconto", 0);

        tvSalarioBruto.setText(String.format("R$ %.2f", valorSalarioBruto));
        tvPlanoSaude.setText(String.format("R$ %d.00", valorPlanoSaude));
        tvTransporte.setText(String.format("R$ %.2f", valorTransporte));
        tvAlimentacao.setText(String.format("R$ %d.00", valorAlimentacao));
        tvRefeicao.setText(String.format("R$ %.2f", valorRefeicao));
        tvInss.setText(String.format("R$ %.2f", valorInss));
        tvIrrf.setText(String.format("R$ %.2f", valorIrrf));
        tvSalarioLiquido.setText(String.format("R$ %.2f", valorSalarioLiquido));
        tvPorcentagem.setText(String.format("%.1f", valorPorcentagem)+"%");

        btnRecalculate.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
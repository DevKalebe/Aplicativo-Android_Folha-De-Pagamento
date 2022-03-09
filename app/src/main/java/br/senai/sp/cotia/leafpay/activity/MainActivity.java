package br.senai.sp.cotia.leafpay.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.rtoshiro.util.format.MaskFormatter;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import br.senai.sp.cotia.leafpay.R;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etSalario, etDependentes;
    private Button btnCalcular;
    private Spinner spnPlanoSaude, spnTransporte, spnAlimentacao, spnRefeicao;

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // pegando as váriaveis dos inputs
        etSalario = findViewById(R.id.et_salario);
        etDependentes = findViewById(R.id.et_dependentes);
        spnPlanoSaude = findViewById(R.id.spn_plano_saude);
        spnTransporte = findViewById(R.id.spn_transporte);
        spnAlimentacao = findViewById(R.id.spn_alimentacao);
        spnRefeicao = findViewById(R.id.spn_refeicao);
        btnCalcular = findViewById(R.id.btn_calcular);


        // Função de click do botão calcular
        btnCalcular.setOnClickListener(view -> {

            // validando os input pra ver se está vazio
            if (etSalario.getText().toString().isEmpty() && etDependentes.getText().toString().isEmpty()) {
                etSalario.setError(getString(R.string.validate_salary));
                etDependentes.setError(getString(R.string.validate_dependents));
            } else if (etSalario.getText().toString().isEmpty()) {
                etSalario.setError(getString(R.string.validate_salary));
            } else if (etDependentes.getText().toString().isEmpty()) {
                etDependentes.setError(getString(R.string.validate_dependents));
            } else {
                // convertendo o valor do salario para double
                double salarioBruto = Double.parseDouble(etSalario.getText().toString());
                // Convertendo o valor do numero de dependentes para int
                int dependentes = Integer.parseInt(etDependentes.getText().toString());
                if (salarioBruto < 0 && dependentes < 0) { // vendo se o salario e o numero de dependentes está negativo
                    etSalario.setError(getString(R.string.validate_salary_negative));
                    etSalario.setError(getString(R.string.validate_dependents_negative));
                } else if (salarioBruto < 0) { // vendo se o salario é negativo
                    etSalario.setError(getString(R.string.validate_salary_negative));
                } else if (dependentes < 0) { // vendo se o numero de dependetes é negativo
                    etSalario.setError(getString(R.string.validate_dependents_negative));
                } else if (spnPlanoSaude.getSelectedItemPosition() == 0) { // vendo se o usúario selecionou o plano de saude
                    Toast.makeText(getBaseContext(), R.string.validate_health, Toast.LENGTH_SHORT).show();
                } else if (spnTransporte.getSelectedItemPosition() == 0) { // vendo se o usúario selecionou a opção de vale transporte
                    Toast.makeText(getBaseContext(), R.string.validate_transport, Toast.LENGTH_SHORT).show();
                } else if (spnAlimentacao.getSelectedItemPosition() == 0) { // vendo se o usúario selecionou a opção de vale alimentação
                    Toast.makeText(getBaseContext(), R.string.validate_feeding, Toast.LENGTH_SHORT).show();
                } else if (spnRefeicao.getSelectedItemPosition() == 0) { // vendo se o ususario selecionou a opção de vale refeição
                    Toast.makeText(getBaseContext(), R.string.validate_meal, Toast.LENGTH_SHORT).show();
                } else {
                    int valorPlanoSaude = 0;
                    switch (spnPlanoSaude.getSelectedItemPosition()) {
                        case 1:
                            if (salarioBruto <= 3000) {
                                valorPlanoSaude = 60;
                            } else {
                                valorPlanoSaude = 80;
                            }
                            break;
                        case 2:
                            if (salarioBruto <= 3000) {
                                valorPlanoSaude = 80;
                            } else {
                                valorPlanoSaude = 110;
                            }
                            break;
                        case 3:
                            if (salarioBruto <= 3000) {
                                valorPlanoSaude = 95;
                            } else {
                                valorPlanoSaude = 135;
                            }
                            break;
                        case 4:
                            if (salarioBruto <= 3000) {
                                valorPlanoSaude = 130;
                            } else {
                                valorPlanoSaude = 180;
                            }
                            break;
                        default:
                            valorPlanoSaude = 0;
                    }
                    Log.i("Valor do Plano de Saude", "Valor plano de Saúde: " + valorPlanoSaude);
                    // Calculando o valor do vale Transporte caso o úsuario obter
                    double valorValeTransporte = 0;
                    switch (spnTransporte.getSelectedItemPosition()) {
                        case 1:
                            valorValeTransporte = salarioBruto * 0.06;
                            break;
                        case 2:
                            valorValeTransporte = 0;
                            break;
                    }
                    Log.i("valorValeTransporte", "valor:" + valorValeTransporte);
                    //  Desenvolvendo a lógica do vale Refeição, caso o usuário obter
                    int valorValeAlimentacao = 0;
                    switch (spnAlimentacao.getSelectedItemPosition()) {
                        case 1:
                            if (salarioBruto <= 3000) {
                                valorValeAlimentacao = 15;
                            } else if (salarioBruto <= 5000) {
                                valorValeAlimentacao = 25;
                            } else {
                                valorValeAlimentacao = 35;
                            }
                            break;
                        case 2:
                            valorValeAlimentacao = 0;
                            break;
                    }
                    // Desenvolvendo a lógica do vale Alimentação, caso o usuário obter
                    double valorValeRefeicao = 0;
                    switch (spnRefeicao.getSelectedItemPosition()) {
                        case 1:
                            if (salarioBruto <= 3000) {
                                valorValeRefeicao = 2.6 * 22;
                            } else if (salarioBruto <= 5000) {
                                valorValeRefeicao = 3.65 * 22;
                            } else {
                                valorValeRefeicao = 6.5 * 22;
                            }
                            break;
                        case 2:
                            valorValeRefeicao = 0;
                            break;
                    }
                    Log.i("Valor vale Alimentação", "" + valorValeAlimentacao);
                    double valorInss = 0;
                    //Obtendo o valor do INSS
                    if (salarioBruto <= 1212) {
                        valorInss = salarioBruto * 0.075;
                    } else if (salarioBruto <= 2427.35) {
                        valorInss = (salarioBruto * 0.09) - 18.18;
                    } else if (salarioBruto <= 3641.03) {
                        valorInss = (salarioBruto * 0.12) - 91;
                    } else if (salarioBruto <= 7087.22) {
                        valorInss = (salarioBruto * 0.14) - 163.82;
                        // tirar duvida se o salario for maior que 7087.22 o valor no inss será 713.08
                    } else {
                        valorInss = 828.39;
                    }
                    Log.i("Valor do INSS", "" + valorInss);
                    //Equação para determinar a base de cálculo do IRRF
                    double baseIrrf = salarioBruto - valorInss - (189.59 * dependentes);
                    //Estrutura para saber o valor do IRRF
                    double valorIrrf;
                    if (baseIrrf <= 1903.98) {
                        valorIrrf = 0;
                    } else if (baseIrrf <= 2826.65) {
                        valorIrrf = (baseIrrf * 0.075) - 142.8;
                    } else if (baseIrrf <= 3751.05) {
                        valorIrrf = (baseIrrf * 0.15) - 354.8;
                    } else if (baseIrrf <= 4664.68) {
                        valorIrrf = (baseIrrf * 0.225) - 636.13;
                    } else {
                        valorIrrf = (baseIrrf * 0.275) - 869.36;
                    }
                    Log.i("Valor do IRRF", "" + valorIrrf);

                    // calculando o salário líquido
                    double salarioLiquido =  salarioBruto - valorInss - valorValeTransporte - valorValeRefeicao - valorValeAlimentacao - valorIrrf - valorPlanoSaude;
                    Log.i("Valor do salário líquido",""+salarioLiquido);

                    //Equação para saber a porcentagem de desconto sobre o sálario bruto
                    double porcentagemDesconto = 1 - salarioLiquido / salarioBruto;
                    porcentagemDesconto = porcentagemDesconto * 100;

                    Intent intent = new Intent(this, ResultadoActivity.class);
                    intent.putExtra("salarioBruto", salarioBruto);
                    intent.putExtra("planoSaude", valorPlanoSaude);
                    intent.putExtra("valeTransporte", valorValeTransporte);
                    intent.putExtra("valeRefeicao", valorValeRefeicao);
                    intent.putExtra("valeAlimentacao", valorValeAlimentacao);
                    intent.putExtra("inss", valorInss);
                    intent.putExtra("irrf", valorIrrf);
                    intent.putExtra("salarioLiquido", salarioLiquido);
                    intent.putExtra("porcentagemDesconto", porcentagemDesconto);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
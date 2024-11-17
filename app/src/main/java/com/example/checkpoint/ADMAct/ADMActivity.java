package com.example.checkpoint.ADMAct;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.checkpoint.R;
import com.example.checkpoint.databinding.ActivityAdmactivityBinding;

public class ADMActivity extends AppCompatActivity {

    private ActivityAdmactivityBinding binding;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdmactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new ADMHomeFragment());

        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configuração do BottomNavigationView
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                replaceFragment(new ADMHomeFragment());
            } else if (itemId == R.id.relatorio) {
                replaceFragment(new ADMRelatorioFragment());
            } else if (itemId == R.id.cadastro) {
                replaceFragment(new ADMCadastroFragment());
            } else if (itemId == R.id.ponto) {
                replaceFragment(new ADMPontoFragment());
            } else if (itemId == R.id.user) {
                replaceFragment(new ADMUserFragment());
            } else {
                return false; // Retorna falso para IDs inesperados
            }
            return true; // Retorna verdadeiro se o ID foi tratado
        });
    }

    /**
     * Método para substituir o fragmento atual pelo fragmento fornecido.
     *
     * @param fragment Fragmento a ser exibido.
     */
    private void replaceFragment(@NonNull Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}
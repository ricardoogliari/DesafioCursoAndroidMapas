package curso.mpgo.com.desafio1;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private TextView txtEndereco;
    private TextView txtLocal;

    private HashMap<String, Posicao> pois = new HashMap<>();

    private Marker markerSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        txtEndereco = (TextView)findViewById(R.id.txtEndereco);
        txtLocal = (TextView)findViewById(R.id.txtLocal);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Posicao posicao = pois.get(marker.getId());
                txtEndereco.setText(posicao.endereco);
                txtLocal.setText(posicao.local);
                markerSelecionado = marker;
                return true;
            }
        });

        // Add a marker in Sydney and move the camera
        LatLng minhaCasa = new LatLng(-16.686827, -49.310416);
        Marker markerCasa = mMap.addMarker(new MarkerOptions().position(minhaCasa).title("Minha Casa"));
        Posicao posicaoCasa = new Posicao();
        posicaoCasa.local = "Minha casa";
        posicaoCasa.endereco = "Rua Vacaria, 181. David Canabarro";
        pois.put(markerCasa.getId(), posicaoCasa);

        LatLng trabalho = new LatLng(-16.709065, -49.314365);
        Marker markerTrabalho = mMap.addMarker(new MarkerOptions().position(trabalho).title("Meu Trabalho"));
        Posicao posicaoTrabalho = new Posicao();
        posicaoTrabalho.local = "Meu Trabalho";
        posicaoTrabalho.endereco = "Rua Morom, 2056. David Canabarro";
        pois.put(markerTrabalho.getId(), posicaoTrabalho);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(minhaCasa));
    }

    public void rota(View view){
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + markerSelecionado.getPosition().latitude+","+
                                markerSelecionado.getPosition().longitude);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    class Posicao {
        public String endereco;
        public String local;
    }
}

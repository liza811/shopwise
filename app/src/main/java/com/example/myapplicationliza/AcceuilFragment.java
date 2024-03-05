package com.example.myapplicationliza;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.myapplicationliza.Adapters.ArticleOccasionAdapter;
import com.example.myapplicationliza.Adapters.BannerAdapter;
import com.example.myapplicationliza.Adapters.CategorieAdapter;
import com.example.myapplicationliza.Adapters.CosmetiqueAdapter;
import com.example.myapplicationliza.Adapters.ElectroniqueAdapter;
import com.example.myapplicationliza.Adapters.ImmobilierAdapter;
import com.example.myapplicationliza.Adapters.VetementAdapter;
import com.example.myapplicationliza.Models.ArticleOccasionModel;
import com.example.myapplicationliza.Models.BannerModel;
import com.example.myapplicationliza.Models.CategorieModel;
import com.example.myapplicationliza.RetrofitApi.ApiClient;
import com.example.myapplicationliza.RetrofitApi.ApiInterface;
import com.example.myapplicationliza.RetrofitApi.users;
import com.example.myapplicationliza.Sessions.SessionManager;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AcceuilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AcceuilFragment extends Fragment implements View.OnClickListener, RecyclerViewInterface,RcCat {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AcceuilFragment() {
        // Required empty public constructor
    }

    String id;
    String iddd;
    RecyclerViewInterface recyclerViewInterface;
    TextView id_utilisateur, article_id, voirToutVetement, voirToutCosmetique, voir_tout_immobilier, voir_tout_electronique, voir_tout_automobile, voir_tout_info, voir_tout_piece,voir_tout_occasion;
    DrawerLayout drawerLayout;

    ImageView navigationBar, ImageBanner, article_image;
    ImageSlider imageSlider;
    NavigationView navigationView;
    private View view;
    EditText search;
    SessionManager sessionManager;

    private RelativeLayout  contacter, vendre;
    private TextView connecter, inscrire, deconnecter, parametre, cos_sm,info_sm,piece_sm,ele_sm,auto_sm,vet_sm,imm_sm;
    /////////// recycler Categorie///////////////
    private RecyclerView recyclerView;
    private CategorieAdapter categorieAdapter;

    private List<CategorieModel> categorieModelList;
    ////////////////////////////////////////////////////
    //////////////////recycler banner////////////

    /////////////////// article occasion//////////////////////
    private RecyclerView RVarticleOccasion, RVCosmetique, RVVetement, RVImmobilier, RVElectronique, RVAutomobile, RVInformatique, RVPiece;
    private List<ArticleOccasionModel> articleOccasionModel, articleModel, vetementModel, immobilierModel, electroniqueMdel, automobileModel, informatiqueModel, pieceModel;
    private ArticleOccasionAdapter articleOccasionAdapter;
    private CosmetiqueAdapter cosmetiqueAdapter;
    private VetementAdapter vetementAdapter;
    private ImmobilierAdapter immobilierAdapter;
    private ElectroniqueAdapter electroniqueAdapter, automobileAdapter, informatiqueAdapter, pieceAdapter;
    /////////////////////////////////////////////////////////////////////
    public static ApiInterface apiInterface;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AcceuilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AcceuilFragment newInstance(String param1, String param2) {
        AcceuilFragment fragment = new AcceuilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getString("id");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_acceuil, container, false);
        sessionManager = new SessionManager(getContext());
//
        String iddd=sessionManager.login();
        //############"  search  ###########################""""



        search= view.findViewById(R.id.search);


        search.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH) {
               String text= search.getText().toString();
                Intent intent = new Intent(getContext(), VoirTout.class);
                intent.putExtra("categgorie", "search");
                intent.putExtra("text", text);
                startActivity(intent);

                return true;
            }
            return false;
        });
        //###############################" VOIR TOUT ##############################################
        voirToutVetement = view.findViewById(R.id.voirToutVetement);
        voirToutCosmetique = view.findViewById(R.id.voirToutCosmetique);
        voir_tout_immobilier = view.findViewById(R.id.voir_tout_immobilier);
        voir_tout_automobile = view.findViewById(R.id.voir_tout_automobile);
        voir_tout_electronique = view.findViewById(R.id.voir_tout_electronique);
        voir_tout_info = view.findViewById(R.id.voir_tout_info);
        voir_tout_occasion = view.findViewById(R.id.voir_tout_occasion);
        voir_tout_occasion.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), VoirTout.class);
            intent.putExtra("categgorie", "Articles d'occasion");
            startActivity(intent);
        });
        voir_tout_piece = view.findViewById(R.id.voir_tout_piece);
        voir_tout_piece.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), VoirTout.class);
            intent.putExtra("categgorie", "Pièces détachées");
            startActivity(intent);
        });
        voir_tout_automobile.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), VoirTout.class);
            intent.putExtra("categgorie", "Automobile");
            startActivity(intent);
        });
        voir_tout_info.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), VoirTout.class);
            intent.putExtra("categgorie", "Informatique");
            startActivity(intent);
        });
        voir_tout_electronique.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), VoirTout.class);
            intent.putExtra("categgorie", "Electronique & Electroménager");
            startActivity(intent);
        });
        voir_tout_immobilier.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), VoirTout.class);
            intent.putExtra("categgorie", "Immobilier");
            startActivity(intent);
        });
        voirToutCosmetique.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), VoirTout.class);
            intent.putExtra("categgorie", "Cosmetique & Beauté");
            startActivity(intent);
        });
        voirToutVetement.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), VoirTout.class);
            intent.putExtra("categgorie", "Vetement");
            startActivity(intent);
        });
//////////////for side layout menu//////////////////////////
        onSetNavigationDrawerEvent();
        /////////////////////////////////////////////////////////
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        //////////////// for recycler view categorie////////////////////////

        Bundle bundle = this.getArguments();
        if (bundle != null) {

         //   Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();


            id_utilisateur = view.findViewById(R.id.id_utilisateurr);
            id_utilisateur.setText(iddd);
        }


        init();
        article_image = view.findViewById(R.id.article_image);
        article_id = view.findViewById(R.id.article_id);
        RVCosmetique = view.findViewById(R.id.RVCosmetique);


        //////////////////////////////////////////////////


        return view;
    }

    private void init() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String s = bundle.getString("id");
            Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();


            id_utilisateur = view.findViewById(R.id.id_utilisateurr);
            id_utilisateur.setText(s);
        }


        //////////////////////// STRIP BANNERS///////////////////////////////////////////////
        imageSlider = view.findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.pub, ScaleTypes.FIT));

        slideModels.add(new SlideModel(R.drawable.b19, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.b20, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.b21, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
        ///////////////////////// CATEGORIE//////////////////////////////////////////////////////////////
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        categorieModelList = new ArrayList<>();

        Call<users> categorieCall = apiInterface.getCategories();

        categorieCall.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> categorieCall, Response<users> response) {
                assert response.body() != null;
                categorieModelList = response.body().getCategorie();

                categorieAdapter = new CategorieAdapter(categorieModelList, getContext(),AcceuilFragment.this::onItemClickk);
                recyclerView.setAdapter(categorieAdapter);
                categorieAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<users> categorieCall, Throwable t) {

            }
        });


        //////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////// BANNER//////////////////////////////////////////////////////////////
     /*  recyclerViewBanner=(RecyclerView) view.findViewById(R.id.recyclerViewBanner);
        LinearLayoutManager linearLayoutManagerBannerr=new LinearLayoutManager(getContext());
        linearLayoutManagerBannerr.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewBanner.setLayoutManager(linearLayoutManagerBannerr);
       bannerModelList=new ArrayList<>();
       bannerModelList.add(new BannerModel(z));
       bannerModelList.add(new BannerModel(R.drawable.shopwise));
       bannerModelList.add(new BannerModel(R.drawable.shopwise));
       bannerModelList.add(new BannerModel(R.drawable.shopwise));
       bannerModelList.add(new BannerModel(R.drawable.shopwise));
        bannerAdapter =new BannerAdapter(bannerModelList,getContext());
        recyclerViewBanner.setAdapter(bannerAdapter);
        bannerAdapter.notifyDataSetChanged();*/

        ///////////////////////////// Article occasion//////////////////////////////////////////////////////////
        RVarticleOccasion = view.findViewById(R.id.RVarticleOccasion);
        LinearLayoutManager linearLayoutManagerBanner = new LinearLayoutManager(getContext());
        linearLayoutManagerBanner.setOrientation(RecyclerView.HORIZONTAL);
        RVarticleOccasion.setLayoutManager(linearLayoutManagerBanner);

        articleOccasionModel = new ArrayList<>();
        Call<users> articleOccasionCall = apiInterface.getArticle_occasion();
        articleOccasionCall.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                articleOccasionModel = response.body().getArticle_occasion();
                articleOccasionAdapter = new ArticleOccasionAdapter(articleOccasionModel, getContext(), AcceuilFragment.this::onItemClick);
                RVarticleOccasion.setAdapter(articleOccasionAdapter);
                articleOccasionAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {

            }
        });


        ///////////////////////////// publicite //////////////////////////////////////////////////////
        ImageBanner = view.findViewById(R.id.ImageBanner);
        Call<users> publiciteCall = apiInterface.getBanners();
        publiciteCall.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> publiciteCall, Response<users> response) {
                Glide.with(getContext()).load(response.body().getPub_image()).into(ImageBanner);
            }

            @Override
            public void onFailure(Call<users> publiciteCall, Throwable t) {

            }
        });
        /////////////////////// article cosmetique///////////////////////////////
        RVCosmetique = view.findViewById(R.id.RVCosmetique);
        LinearLayoutManager linearLayoutManagerCosmetique = new LinearLayoutManager(getContext());
        linearLayoutManagerCosmetique.setOrientation(RecyclerView.HORIZONTAL);
        RVCosmetique.setLayoutManager(linearLayoutManagerCosmetique);

        articleModel = new ArrayList<>();
        //  Call<users> articleOccasionCall= apiInterface.getArticle_occasion();
        Call<users> cosmetiqueCall = apiInterface.getArticle();
        cosmetiqueCall.enqueue(new Callback<users>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                articleModel = response.body().getArticle_cosmetique();
                cosmetiqueAdapter = new CosmetiqueAdapter(articleModel, getContext(), AcceuilFragment.this::onItemClick);
                RVCosmetique.setAdapter(cosmetiqueAdapter);
                cosmetiqueAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {

            }
        });
        /////////////////////// vetements///////////////////////////////
        RVVetement = view.findViewById(R.id.RVVetement);
        LinearLayoutManager linearLayoutManagerVetement = new LinearLayoutManager(getContext());
        linearLayoutManagerVetement.setOrientation(RecyclerView.HORIZONTAL);
        RVVetement.setLayoutManager(linearLayoutManagerVetement);

        vetementModel = new ArrayList<>();
        Call<users> vetementCall = apiInterface.getVetement();
        vetementCall.enqueue(new Callback<users>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<users> vetementCall, Response<users> response) {
                vetementModel = response.body().getArticle_vetement();
                vetementAdapter = new VetementAdapter(vetementModel, getContext(), AcceuilFragment.this::onItemClick);
                RVVetement.setAdapter(vetementAdapter);
                vetementAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<users> vetementCall, Throwable t) {

            }
        });

        /////////////////////// immobilier///////////////////////////////
        RVImmobilier = view.findViewById(R.id.RVImmobilier);
        LinearLayoutManager linearLayoutManagerImmobilier = new LinearLayoutManager(getContext());
        linearLayoutManagerImmobilier.setOrientation(RecyclerView.HORIZONTAL);
        RVImmobilier.setLayoutManager(linearLayoutManagerImmobilier);

        immobilierModel = new ArrayList<>();
        Call<users> immobilierCall = apiInterface.getImmobilier();
        immobilierCall.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> immobilierCall, Response<users> response) {
                immobilierModel = response.body().getArticle_imm();
                immobilierAdapter = new ImmobilierAdapter(immobilierModel, getContext(), AcceuilFragment.this::onItemClick);
                RVImmobilier.setAdapter(immobilierAdapter);
                immobilierAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<users> immobilierCall, Throwable t) {

            }
        });
        /////////////////////// electronique///////////////////////////////
        RVElectronique = view.findViewById(R.id.RVElectronique);
        LinearLayoutManager linearLayoutManagerElectronique = new LinearLayoutManager(getContext());
        linearLayoutManagerElectronique.setOrientation(RecyclerView.HORIZONTAL);
        RVElectronique.setLayoutManager(linearLayoutManagerElectronique);

        electroniqueMdel = new ArrayList<>();
        Call<users> electroniqueCall = apiInterface.getelectronique();
        electroniqueCall.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> electroniqueCall, Response<users> response) {
                electroniqueMdel = Objects.requireNonNull(response.body()).getArticle_ele();
                electroniqueAdapter = new ElectroniqueAdapter(electroniqueMdel, getContext(), AcceuilFragment.this::onItemClick);
                RVElectronique.setAdapter(electroniqueAdapter);
                electroniqueAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<users> electroniqueCall, Throwable t) {

            }
        });
        /////////////////////// automobile///////////////////////////////
        RVAutomobile = view.findViewById(R.id.RVAutomobile);
        LinearLayoutManager linearLayoutManagerAutomobile = new LinearLayoutManager(getContext());
        linearLayoutManagerAutomobile.setOrientation(RecyclerView.HORIZONTAL);
        RVAutomobile.setLayoutManager(linearLayoutManagerAutomobile);

        automobileModel = new ArrayList<>();
        Call<users> automobileCall = apiInterface.getAutomobile();
        automobileCall.enqueue(new Callback<users>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<users> automobileCall, Response<users> response) {
                automobileModel = response.body().getArticle_auto();
                automobileAdapter = new ElectroniqueAdapter(automobileModel, getContext(), AcceuilFragment.this::onItemClick);
                RVAutomobile.setAdapter(automobileAdapter);
                automobileAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<users> automobileCall, Throwable t) {

            }
        });
        /////////////////////// info///////////////////////////////
        RVInformatique = view.findViewById(R.id.RVInformatique);
        LinearLayoutManager linearLayoutManagerInformatique = new LinearLayoutManager(getContext());
        linearLayoutManagerInformatique.setOrientation(RecyclerView.HORIZONTAL);
        RVInformatique.setLayoutManager(linearLayoutManagerInformatique);

        informatiqueModel = new ArrayList<>();
        Call<users> informatiqueCall = apiInterface.getInformatique();
        informatiqueCall.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> informatiqueCall, Response<users> response) {
                informatiqueModel = response.body().getArticle_info();
                informatiqueAdapter = new ElectroniqueAdapter(informatiqueModel, getContext(), AcceuilFragment.this::onItemClick);
                RVInformatique.setAdapter(informatiqueAdapter);
                informatiqueAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<users> informatiqueCall, Throwable t) {

            }
        });
        /////////////////////// piece///////////////////////////////
        RVPiece = view.findViewById(R.id.RVPiece);
        LinearLayoutManager linearLayoutManagerPiece = new LinearLayoutManager(getContext());
        linearLayoutManagerPiece.setOrientation(RecyclerView.HORIZONTAL);
        RVPiece.setLayoutManager(linearLayoutManagerPiece);

        pieceModel = new ArrayList<>();
        Call<users> pieceCall = apiInterface.getPiece();
        pieceCall.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> pieceCall, Response<users> response) {
                assert response.body() != null;
                pieceModel = response.body().getArticle_piece();
                pieceAdapter = new ElectroniqueAdapter(pieceModel, getContext(), AcceuilFragment.this::onItemClick);
                RVPiece.setAdapter(pieceAdapter);
                pieceAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<users> pieceCall, Throwable t) {

            }
        });


    }

    private void onSetNavigationDrawerEvent() {
        drawerLayout = view.findViewById(R.id.drawer_layout);
        navigationView = view.findViewById(R.id.navigationView);
        navigationBar = view.findViewById(R.id.navigationBar);


        contacter = view.findViewById(R.id.contacter);
        vendre = view.findViewById(R.id.vendre);
        connecter = view.findViewById(R.id.connecter);
        inscrire = view.findViewById(R.id.inscrire);
        deconnecter = view.findViewById(R.id.deconnecter);
        parametre = view.findViewById(R.id.parametre);
        cos_sm = view.findViewById(R.id.cos_sm);
        info_sm = view.findViewById(R.id.info_sm);
        vet_sm = view.findViewById(R.id.vet_sm);
        auto_sm = view.findViewById(R.id.auto_sm);
        ele_sm = view.findViewById(R.id.ele_sm);
        imm_sm = view.findViewById(R.id.imm_sm);
        piece_sm = view.findViewById(R.id.piece_sm);
        navigationBar.setOnClickListener(this);

        contacter.setOnClickListener(this);
        vendre.setOnClickListener(this);
        connecter.setOnClickListener(this);
        deconnecter.setOnClickListener(this);
        parametre.setOnClickListener(this);
        cos_sm.setOnClickListener(this);
        info_sm.setOnClickListener(this);
        vet_sm.setOnClickListener(this);
        piece_sm.setOnClickListener(this);
        ele_sm.setOnClickListener(this);
        imm_sm.setOnClickListener(this);
        auto_sm.setOnClickListener(this);
        inscrire.setOnClickListener(this);


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.navigationBar:
                drawerLayout.openDrawer(navigationView, true);
                break;

            case R.id.contacter:

                //  getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new Vendez_sur_shopwise()).commit();
                Intent intent = new Intent(getContext(), Contacter_nous.class);
                startActivity(intent);

                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.vendre:
                Fragment fr;
                fr = new Vendez_sur_shopwise();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.framelayout, fr);
                fragmentTransaction.commit();
                //  getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new Vendez_sur_shopwise()).commit();


                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.connecter:
                Intent intenttt = new Intent(getContext(), loginActivity.class);
                startActivity(intenttt);
                getActivity().finish();

                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.inscrire:
                Intent intentt = new Intent(getContext(), inscriptionActivity.class);
                startActivity(intentt);
                getActivity().finish();

                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.deconnecter:
                logout();

                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.parametre:
                Intent intent1=new Intent(getContext(),ParametreProfil.class);
                intent1.putExtra("id",iddd);


                startActivity(intent1);

                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.cos_sm:
                Intent intenntt = new Intent(getContext(), VoirTout.class);
                intenntt.putExtra("categgorie", "Cosmetique & Beauté");
                startActivity(intenntt);
                drawerLayout.closeDrawer(navigationView, true);
                break;

            case R.id.info_sm:
                Intent intennt1 = new Intent(getContext(), VoirTout.class);
                intennt1.putExtra("categgorie", "Informatique");
                startActivity(intennt1);
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.auto_sm:
                Intent intennt2 = new Intent(getContext(), VoirTout.class);
                intennt2.putExtra("categgorie", "Automobile");
                startActivity(intennt2);
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.vet_sm:
                Intent intennt3 = new Intent(getContext(), VoirTout.class);
                intennt3.putExtra("categgorie", "Vetement");
                startActivity(intennt3);
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.ele_sm:
                Intent intennt4 = new Intent(getContext(), VoirTout.class);
                intennt4.putExtra("categgorie", "Electronique & Electroménager");
                startActivity(intennt4);
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.piece_sm:
                Intent intennt5 = new Intent(getContext(), VoirTout.class);
                intennt5.putExtra("categgorie", "Pièces détachées");
                startActivity(intennt5);
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.imm_sm:
                Intent intennt6= new Intent(getContext(), VoirTout.class);
                intennt6.putExtra("categgorie", "Immobilier");
                startActivity(intennt6);
                drawerLayout.closeDrawer(navigationView, true);
                break;
        }

    }

    public void details(String id) {
        //Toast.makeText(getContext(), id, Toast.LENGTH_SHORT).show();
    }

    private void logout() {
        //DELETE SESSION
        sessionManager.editor.clear();
        sessionManager.editor.commit();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (sessionManager.isLogin()) {
            connecter.setVisibility(View.GONE);
            deconnecter.setVisibility(View.VISIBLE);
            inscrire.setVisibility(View.GONE);
            parametre.setVisibility(View.VISIBLE);
          iddd=sessionManager.login();

            id_utilisateur = view.findViewById(R.id.id_utilisateurr);
            id_utilisateur.setText(iddd);
            // Hide the keyboard when the fragment starts
            search= view.findViewById(R.id.search);
            search.clearFocus();
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
            }





        }
    }


    @Override
    public void onItemClick(ArticleOccasionModel articleOccasionModel) {
        String pub_id = articleOccasionModel.getPub_id();
        Call<users> call = apiInterface.performDetails(pub_id);
        call.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                if (response.body().getResponse().equals("ok")) {
                    String id1 = response.body().getPub_id();
                    String pub_title = response.body().getPub_title();
                    String pub_image = response.body().getPub_image();
                    String prix = response.body().getPrix();
                    String descriptionn = response.body().getDescriptionn();
                    Intent intent = new Intent(getContext(), DetailsArticle.class);
                    intent.putExtra("pubb_title", pub_title);
                    intent.putExtra("pubb_id", id1);
                    intent.putExtra("pub_image", pub_image);
                    intent.putExtra("prix", prix);
                    intent.putExtra("descriptionn", descriptionn);
                    intent.putExtra("ut_id", iddd);


                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {

            }
        });


    }


    @Override
    public void onItemClickk(CategorieModel categorieModel) {
        String pub_id = categorieModel.getCat_title();

        Intent intenntt = new Intent(getContext(), VoirTout.class);
        intenntt.putExtra("categgorie", pub_id);
        startActivity(intenntt);
    }

}
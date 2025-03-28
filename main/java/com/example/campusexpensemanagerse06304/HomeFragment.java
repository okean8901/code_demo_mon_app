package com.example.campusexpensemanagerse06304;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.campusexpensemanagerse06304.adapter.ProductListAdaper;
import com.example.campusexpensemanagerse06304.model.Products;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements SearchView.OnQueryTextListener {

    ListView listview ;
    SearchView searchView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listview = view.findViewById(R.id.listView);
        searchView = view.findViewById(R.id.searchView);
        //tao du lieu mẫu để test
        List<Products> products = new ArrayList<>();
        products.add(new Products(1, "Realme GT Neo 2", "https://cdn.mobilecity.vn/mobilecity-vn/images/2021/09/w300/realme-gt-neo-2-mint.jpg.webp", 5600000));
        products.add(new Products(2, "Realme GT Neo 3", "https://cdn.mobilecity.vn/mobilecity-vn/images/2022/06/w300/realme-gt-neo-3-xanh-tim-1.jpg.webp", 8200000));
        products.add(new Products(3, "Realme GT Neo 5", "https://cdn.mobilecity.vn/mobilecity-vn/images/2023/02/w300/realme-gt-neo-5-5g-trang.jpg.webp", 6300000));
        products.add(new Products(4, "Realme GT Neo 6", "https://cdn.mobilecity.vn/mobilecity-vn/images/2024/05/w300/realme-gt-neo-6-tim.jpg.webp", 7400000));
        products.add(new Products(5, "Realme Neo 7", "https://cdn.mobilecity.vn/mobilecity-vn/images/2024/12/w300/realme-neo7-xanh.jpg.webp", 1020000));
        products.add(new Products(6, "Realme GT Neo 2", "https://cdn.mobilecity.vn/mobilecity-vn/images/2021/09/w300/realme-gt-neo-2-mint.jpg.webp", 5600000));
        products.add(new Products(7, "Realme GT Neo 3", "https://cdn.mobilecity.vn/mobilecity-vn/images/2022/06/w300/realme-gt-neo-3-xanh-tim-1.jpg.webp", 8200000));
        products.add(new Products(8, "Realme GT Neo 5", "https://cdn.mobilecity.vn/mobilecity-vn/images/2023/02/w300/realme-gt-neo-5-5g-trang.jpg.webp", 6300000));
        products.add(new Products(9, "Realme GT Neo 6", "https://cdn.mobilecity.vn/mobilecity-vn/images/2024/05/w300/realme-gt-neo-6-tim.jpg.webp", 7400000));
        products.add(new Products(10, "Realme Neo 7", "https://cdn.mobilecity.vn/mobilecity-vn/images/2024/12/w300/realme-neo7-xanh.jpg.webp", 1020000));
        products.add(new Products(11, "Realme GT Neo 2", "https://cdn.mobilecity.vn/mobilecity-vn/images/2021/09/w300/realme-gt-neo-2-mint.jpg.webp", 5600000));
        products.add(new Products(12, "Realme GT Neo 3", "https://cdn.mobilecity.vn/mobilecity-vn/images/2022/06/w300/realme-gt-neo-3-xanh-tim-1.jpg.webp", 8200000));
        products.add(new Products(13, "Realme GT Neo 5", "https://cdn.mobilecity.vn/mobilecity-vn/images/2023/02/w300/realme-gt-neo-5-5g-trang.jpg.webp", 6300000));
        products.add(new Products(14, "Realme GT Neo 6", "https://cdn.mobilecity.vn/mobilecity-vn/images/2024/05/w300/realme-gt-neo-6-tim.jpg.webp", 7400000));
        products.add(new Products(15, "Realme Neo 7", "https://cdn.mobilecity.vn/mobilecity-vn/images/2024/12/w300/realme-neo7-xanh.jpg.webp", 1020000));
        products.add(new Products(16, "Realme GT Neo 2", "https://cdn.mobilecity.vn/mobilecity-vn/images/2021/09/w300/realme-gt-neo-2-mint.jpg.webp", 5600000));
        products.add(new Products(17, "Realme GT Neo 3", "https://cdn.mobilecity.vn/mobilecity-vn/images/2022/06/w300/realme-gt-neo-3-xanh-tim-1.jpg.webp", 8200000));
        products.add(new Products(18, "Realme GT Neo 5", "https://cdn.mobilecity.vn/mobilecity-vn/images/2023/02/w300/realme-gt-neo-5-5g-trang.jpg.webp", 6300000));
        products.add(new Products(19, "Realme GT Neo 6", "https://cdn.mobilecity.vn/mobilecity-vn/images/2024/05/w300/realme-gt-neo-6-tim.jpg.webp", 7400000));
        products.add(new Products(20, "Realme Neo 7", "https://cdn.mobilecity.vn/mobilecity-vn/images/2024/12/w300/realme-neo7-xanh.jpg.webp", 1020000));

        ProductListAdaper adapter = new ProductListAdaper(getActivity(), products);
        listview.setAdapter(adapter);
        listview.setTextFilterEnabled(true);
        setupSearchView();

        listview.setClickable(true);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Products pd = (Products) listview.getItemAtPosition(position);
                String name = pd.get_name();
                int idPd = pd.get_id();
                double price = pd.get_price();
                String mess = idPd + " " + name + " " + price;
                Toast.makeText(getActivity(),mess, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void setupSearchView(){
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener((SearchView.OnQueryTextListener) this);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("Search product");
    }

    @Override
public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(TextUtils.isEmpty(newText)){
            listview.clearTextFilter();
        }else{
            listview.setFilterText(newText);
        }
        return true;
    }
}
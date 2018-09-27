package pe.projects.rappi.testrappi.app.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import pe.projects.rappi.testrappi.Constants;
import pe.projects.rappi.testrappi.R;
import pe.projects.rappi.testrappi.app.ui.listener.MoviesListListener;
import pe.projects.rappi.testrappi.app.ui.util.DateUtil;
import pe.projects.rappi.testrappi.app.ui.util.NetworkUtils;
import pe.projects.rappi.testrappi.domain.model.MovieModel;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> implements Filterable{
    private Context context;
    private MoviesListListener listener;
    private List<MovieModel> movieModelList;
    private List<MovieModel> movieModelListFiltered;
    private boolean loading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public MoviesAdapter(Context context, final MoviesListListener listener,
                         List<MovieModel> movieModelList,
                         RecyclerView rviMovies){
        this.context = context;
        this.listener = listener;
        this.movieModelList = movieModelList;
        final GridLayoutManager gridLayoutManager = (GridLayoutManager) rviMovies.getLayoutManager();
        if(NetworkUtils.isOnline(context)){
            rviMovies.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    totalItemCount = gridLayoutManager.getItemCount();
                    lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
                    if(!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)){
                        listener.loadMoreMovies();
                        loading = true;
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.row_movie, parent, false);
        return new MoviesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MoviesViewHolder holder, int position) {
        final MovieModel movieModel = movieModelList.get(position);
        if(movieModel != null){
            holder.tviMovieTitle.setText(movieModel.getTitle());
            holder.tviMovieDate.setText(DateUtil.formatDate(movieModel.getReleaseDate(), Constants.INPUT_FORMAT_DATE, Constants.OUTPUT_FORMAT_DATE));
            Picasso.with(context).load(movieModel.getBackdropPath()).error(R.drawable.error_loading).
                    placeholder(R.drawable.placeholder_loading).fit().into(holder.iviMoviePhoto);
            holder.claMovie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.goToMovieDetail(movieModel);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return movieModelList.size();
    }

    public void setLoaded() {
        loading = false;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    movieModelListFiltered = movieModelList;
                } else {
                    List<MovieModel> filteredList = new ArrayList<>();
                    for (MovieModel row : movieModelList) {
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase()) || row.getTitle().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }
                    movieModelListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = movieModelListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                movieModelListFiltered = (ArrayList<MovieModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout claMovie;
        private ImageView iviMoviePhoto;
        private TextView tviMovieTitle;
        private TextView tviMovieDate;

        public MoviesViewHolder(View view) {
            super(view);
            claMovie = view.findViewById(R.id.claMovie);
            iviMoviePhoto = view.findViewById(R.id.iviMoviePhoto);
            tviMovieTitle = view.findViewById(R.id.tviMovieTitle);
            tviMovieDate = view.findViewById(R.id.tviMovieDate);
        }
    }
}

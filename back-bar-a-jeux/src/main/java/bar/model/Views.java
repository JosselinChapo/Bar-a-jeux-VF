package bar.model;


public class Views {

	public static class ViewBase {
	}
	
	public static class ViewConso extends ViewBase{	
	}
	
	public static class ViewCommandeConso extends ViewBase {	
	}

	public static class ViewCompte extends ViewBase {	
	}
	
	public static class ViewClient extends ViewCompte {
	}

	public static class ViewAdmin extends ViewCompte {
	}

	public static class ViewReservation extends ViewBase {
	}
	
	public static class ViewTableBar extends ViewBase {	
	}

	public static class ViewCommandeJeu extends ViewBase{
	}
	
	public static class ViewAchatJeu extends ViewBase{
	}
	
	public static class ViewJeu extends ViewBase{
	}
	
}

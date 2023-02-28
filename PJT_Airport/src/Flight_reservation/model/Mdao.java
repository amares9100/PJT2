package Flight_reservation.model;


public class Mdao extends Dao{
	private static Mdao mdao = new Mdao();
	private Mdao() {}
	public static Mdao getInstance() { return mdao;}
	
	Member memberDto = new Member();
	
	public boolean idCheck( String mid ) {
		String sql = "select * from member where mid = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString( 1 , mid );
			rs = ps.executeQuery();
			if( rs.next() ) { return true; } 
			else { return false; }
		}
		catch (Exception e) {System.out.println(e);}
		return true; 
	}
	
	public int login( String mid , String mpw  ) {
		String sql = "select * from member where mid = ? and mpw = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString( 1 , mid );
			ps.setString( 2 , mpw );
			rs = ps.executeQuery();
			if( rs.next() ) {
				return rs.getInt( 1 );
			}else { 
				return 0; 
			}
		}catch (Exception e) {System.out.println(e);}
		
		return 0;
	}
	
	public int signup( Member memberDto ) {
		String sql ="insert into member( mid , mpw , mname , mphone )"
				+ " values(  ? , ? , ? , ? )";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString( 1 , memberDto.getMid() );		
			ps.setString( 2 , memberDto.getMpw() );		
			ps.setString( 3 , memberDto.getMname() );	
			ps.setString( 4 , memberDto.getMphone() );	
			ps.executeUpdate();
			return 1;  
		}catch (Exception e) {System.out.println(e);}
		return 3; 
	}
}
